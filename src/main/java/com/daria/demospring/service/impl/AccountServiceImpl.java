package com.daria.demospring.service.impl;

import com.daria.demospring.model.Account;
import com.daria.demospring.repository.AccountRepository;
import com.daria.demospring.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account register(Account account) {
        Account account1 = accountRepository.save(account);
        accountRepository.flush();
        log.info("IN AccountServiceImpl save {}", account);
        return account1;
    }

    @Override
    public List<Account> getAll() {
        List<Account> result = accountRepository.findAll();
        log.info("IN getAll - {} accounts found", result.size());
        return result;
    }

    @Override
    public Account findById(Long id) {
        Account result = accountRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no account found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        log.info("IN AccountServiceImpl delete {}", id);
        accountRepository.deleteById(id);
    }
}
