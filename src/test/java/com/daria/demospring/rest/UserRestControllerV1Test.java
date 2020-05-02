package com.daria.demospring.rest;

import com.daria.demospring.dto.UserDto;
import com.daria.demospring.model.User;
import com.daria.demospring.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class UserRestControllerV1Test {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserRestControllerV1 userRestControllerV1;

    private User user = new User();

    @Test
    void getUserById() {
        when(userService.findById(user.getId())).thenReturn(user);
        ResponseEntity<UserDto> responseEntity = userRestControllerV1.getUserById(user.getId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateUser() {
        ResponseEntity<UserDto> responseEntity = userRestControllerV1.updateUser(user);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteUser() {
        when(userService.findById(user.getId())).thenReturn(user);
        ResponseEntity responseEntity = userRestControllerV1.deleteUser(user.getId());
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userService.getAll()).thenReturn(users);
        ResponseEntity<List<User>> response = userRestControllerV1.getAllUsers();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}