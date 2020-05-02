package com.daria.demospring.rest;

import com.daria.demospring.model.Account;
import com.daria.demospring.service.AccountService;
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
class AccountRestControllerV1Test {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountRestControllerV1 accountRestControllerV1;

    private Account account = new Account();

    @Test
    void getAccount() {
        when(accountService.findById(account.getId())).thenReturn(account);
        ResponseEntity responseEntity = accountRestControllerV1.getAccount(account.getId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void saveAccount() {
        ResponseEntity responseEntity = accountRestControllerV1.saveAccount(account);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void updateAccount() {
        ResponseEntity responseEntity = accountRestControllerV1.updateAccount(account);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteAccount() {
        when(accountService.findById(account.getId())).thenReturn(account);
        ResponseEntity responseEntity = accountRestControllerV1.deleteAccount(account.getId());
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account());
        when(accountService.getAll()).thenReturn(accounts);
        ResponseEntity<List<Account>> response = accountRestControllerV1.getAllAccounts();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}