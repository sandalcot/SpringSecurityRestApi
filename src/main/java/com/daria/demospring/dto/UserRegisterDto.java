package com.daria.demospring.dto;

import com.daria.demospring.model.ConfirmationPhone;
import com.daria.demospring.model.Phone;
import com.daria.demospring.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterDto {
    private Long id;
    private String username;
    private String password;
    private Phone phone;

    public User getUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    public Phone getPhone() {
        Phone phone = new Phone();
        phone.setPhoneNumber(phone.getPhoneNumber());
        phone.setConfirmationPhone(ConfirmationPhone.NOT_CONFIRMED);
        return phone;
    }
}
