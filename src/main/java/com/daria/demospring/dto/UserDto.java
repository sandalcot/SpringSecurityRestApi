package com.daria.demospring.dto;

import com.daria.demospring.model.Phone;
import com.daria.demospring.model.Role;
import com.daria.demospring.model.Status;
import com.daria.demospring.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private Date created;
    private Date updated;
    private Date lastPassChange;
    private Status status;
    private Phone phone;
    private List<Role> roles;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setCreated(user.getCreated());
        userDto.setUpdated(user.getUpdated());
        userDto.setLastPassChange(user.getLastPassChange());
        userDto.setStatus(user.getStatus());
        userDto.setPhone(user.getPhone());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
