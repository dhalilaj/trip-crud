package com.lufthansa.tripcrud.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

}