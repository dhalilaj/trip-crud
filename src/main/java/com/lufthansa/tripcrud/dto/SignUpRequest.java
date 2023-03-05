package com.lufthansa.tripcrud.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignUpRequest {

	@NotNull
	private String username;

	@NotNull
	private String password;

	@NotNull
	private Set<String> role;

}