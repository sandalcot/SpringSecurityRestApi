package com.daria.demospring.service.impl;

import com.daria.demospring.model.Account;
import com.daria.demospring.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account account = new Account();

    @Test
    void register() {
        accountService.register(account);
        verify(accountRepository).save(account);
    }

    @Test
    void getAll() {
        List<Account> accounts = new ArrayList<>();
        when(accountRepository.findAll()).thenReturn(accounts);
        accountService.getAll();
        verify(accountRepository).findAll();
    }

    @Test
    void findById() {
        account.setId(1l);
        accountService.findById(1l);
        verify(accountRepository).findById(account.getId());
    }

    @Test
    void delete() {
        account.setId(1l);
        accountService.delete(1l);
        verify(accountRepository).deleteById(account.getId());
    }
}