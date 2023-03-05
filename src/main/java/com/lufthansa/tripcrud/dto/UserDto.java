package com.lufthansa.tripcrud.dto;

import com.lufthansa.tripcrud.entity.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Set<Role> role;

}
