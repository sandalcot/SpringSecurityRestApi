package com.daria.demospring.rest;

import com.daria.demospring.dto.AccountDto;
import com.daria.demospring.model.Account;
import com.daria.demospring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/account/")
public class AccountRestControllerV1 {
    private final AccountService accountService;

    @Autowired
    public AccountRestControllerV1(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("id") Long accountId) {
        Account account = this.accountService.findById(accountId);

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AccountDto accountDto = AccountDto.fromAccount(account);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PostMapping(value = "/saveAccount")
    public ResponseEntity<Account> saveAccount(@RequestBody @Valid Account account) {
        Account account1 = this.accountService.register(account);
        return new ResponseEntity<>(account1, HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateAccount")
    public ResponseEntity<Account> updateAccount(@RequestBody @Valid Account account) {
        this.accountService.register(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable("id") Long id) {
        Account account = this.accountService.findById(id);

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.accountService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/allAccounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = this.accountService.getAll();

        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
