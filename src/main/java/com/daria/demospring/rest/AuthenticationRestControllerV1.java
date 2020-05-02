package com.daria.demospring.rest;

import com.daria.demospring.dto.AuthenticationRequestDto;
import com.daria.demospring.dto.UserRegisterDto;
import com.daria.demospring.dto.VerifyDto;
import com.daria.demospring.model.ConfirmationPhone;
import com.daria.demospring.model.Phone;
import com.daria.demospring.model.Status;
import com.daria.demospring.model.User;
import com.daria.demospring.security.jwt.JwtTokenProvider;
import com.daria.demospring.service.PhoneService;
import com.daria.demospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final PhoneService phoneService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PhoneService phoneService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.phoneService = phoneService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<Map<Object, Object>> registerUser(@RequestBody UserRegisterDto userRegisterDto) {

        User user = userService.findByUsername(userRegisterDto.getUsername());

        if (user != null) {
            throw new BadCredentialsException("User with username: " + userRegisterDto.getUsername() + " already exists");
        }

        user = userRegisterDto.getUser();

        Phone checkPhone = phoneService.findPhoneNumber(userRegisterDto.getPhone().getPhoneNumber());

        if (checkPhone != null) {
            throw new UsernameNotFoundException("User with phone number: " + checkPhone + " already exists");
        }

        checkPhone = userRegisterDto.getPhone();

        checkPhone.setVerificationCode(phoneService.getVerificationCode());
        user.setPhone(checkPhone);
        checkPhone.setUser(user);
        userService.register(user);
        phoneService.register(checkPhone);
        phoneService.sendCodeSms(checkPhone);
        Map<Object, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("verificationCode", checkPhone.getVerificationCode());
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "verify")
    public ResponseEntity verify(@RequestBody VerifyDto verifyDto) {
        User user = userService.findByUsername(verifyDto.getUsername());
        if (user == null) {
            return ResponseEntity.badRequest().body("User with username: " + verifyDto.getUsername() + " not found");
        }
        Phone phone = user.getPhone();
        if (phone.getVerificationCode() != verifyDto.getCode()) {
            return ResponseEntity.badRequest().body("VerificationCode is incorrect!");
        }
        phone.setConfirmationPhone(ConfirmationPhone.CONFIRMED);
        user.setStatus(Status.ACTIVE);
        phoneService.register(phone);
        userService.register(user);
        return ResponseEntity.ok().body("Verification was successful");
    }
}
