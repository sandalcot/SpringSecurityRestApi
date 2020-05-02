package com.daria.demospring.dto;

import com.daria.demospring.model.Account;
import com.daria.demospring.model.Developer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDto {
    private Long id;
    private Date createdData;
    private Developer developer;

    public static AccountDto fromAccount(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setCreatedData(account.getCreatedDate());
        accountDto.setDeveloper(account.getDeveloper());
        return accountDto;
    }
}
