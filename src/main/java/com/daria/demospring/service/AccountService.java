package com.daria.demospring.service;

import com.daria.demospring.model.Account;

import java.util.List;

public interface AccountService {

    Account register(Account account);

    List<Account> getAll();

    Account findById(Long id);

    void delete(Long id);
}
