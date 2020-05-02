package com.daria.demospring.rest;

import com.daria.demospring.dto.AuthenticationRequestDto;
import com.daria.demospring.dto.UserRegisterDto;
import com.daria.demospring.dto.VerifyDto;
import com.daria.demospring.model.Phone;
import com.daria.demospring.model.User;
import com.daria.demospring.security.jwt.JwtTokenProvider;
import com.daria.demospring.service.PhoneService;
import com.daria.demospring.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AuthenticationRestControllerV1Test {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private UserService userService;
    @Mock
    private PhoneService phoneService;

    @InjectMocks
    private AuthenticationRestControllerV1 authenticationRestController;

    private User user = new User();
    private Phone phone = new Phone();

    @Test
    void login() {
        AuthenticationRequestDto userDto = new AuthenticationRequestDto();
        user.setUsername("login");
        user.setPassword("password");
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        when(userService.findByUsername("login")).thenReturn(user);
        when(jwtTokenProvider.createToken("login", user.getRoles())).thenReturn("token.login");

        ResponseEntity response = authenticationRestController.login(userDto);
        Mockito.verify(authenticationManager, times(1))
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        Assertions.assertEquals("{username=login, token=token.login}", response.getBody().toString());
        Assertions.assertEquals("200 OK", response.getStatusCode().toString());
    }

    @Test
    void registerUser() {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setUsername("user");
        userRegisterDto.setPhone(phone);

        when(userService.findByUsername("user")).thenReturn(null);
        when(phoneService.findPhoneNumber(userRegisterDto.getPhone().getPhoneNumber())).thenReturn(null);
        when(phoneService.getVerificationCode()).thenReturn("wecfwe3r3");

        ResponseEntity registration = authenticationRestController.registerUser(userRegisterDto);
        Assertions.assertEquals("{username=user, verificationCode=wecfwe3r3}", registration.getBody().toString());
    }

    @Test
    void verify() {
        VerifyDto verifyDto = new VerifyDto();
        verifyDto.setUsername("login");
        verifyDto.setCode("21312313");
        Phone phone = new Phone();
        phone.setVerificationCode("yytfrfY213FYvyggub");
        user.setPhone(phone);
        when(userService.findByUsername(verifyDto.getUsername())).thenReturn(user);
        ResponseEntity verify = authenticationRestController.verify(verifyDto);
        Assertions.assertEquals("VerificationCode is incorrect!", verify.getBody().toString());
    }
}