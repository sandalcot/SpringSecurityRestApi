package com.daria.demospring.service.impl;

import com.daria.demospring.model.Role;
import com.daria.demospring.model.User;
import com.daria.demospring.repository.RoleRepository;
import com.daria.demospring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user = new User();
    private Role role = new Role();

    @Test
    void register() {
        user.setPassword("3223d2d2d");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(role);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn(new StringBuilder(user.getPassword()).reverse().toString());
        userService.register(user);
        verify(userRepository).save(user);
    }

    @Test
    void findById() {
        user.setId(1l);
        userService.findById(1l);
        verify(userRepository).findById(user.getId());
    }

    @Test
    void delete() {
        user.setId(1l);
        userService.delete(1l);
        verify(userRepository).deleteById(user.getId());
    }

    @Test
    void getAll() {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);
        userService.getAll();
        verify(userRepository).findAll();
    }
}