package com.lufthansa.tripcrud.services;

import com.lufthansa.tripcrud.dto.CreateUserRequest;
import com.lufthansa.tripcrud.dto.SignUpRequest;
import com.lufthansa.tripcrud.dto.UserDto;
import com.lufthansa.tripcrud.entity.Role;
import com.lufthansa.tripcrud.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<UserDto> findByUsername(String username);

    List<User> findAll();


    public void createUser(CreateUserRequest createUserRequest);

}
