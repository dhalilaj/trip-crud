package com.lufthansa.tripcrud.services.impl;


import com.lufthansa.tripcrud.converter.UserConverter;
import com.lufthansa.tripcrud.dto.CreateUserRequest;
import com.lufthansa.tripcrud.dto.UserDto;
import com.lufthansa.tripcrud.entity.Role;
import com.lufthansa.tripcrud.entity.RoleName;
import com.lufthansa.tripcrud.entity.User;
import com.lufthansa.tripcrud.repository.RoleRepository;
import com.lufthansa.tripcrud.repository.UserRepository;
import com.lufthansa.tripcrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserConverter userConverter;

    private RoleRepository roleRepository;
    PasswordEncoder encoder;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository, UserConverter userConverter, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public List<UserDto> findByUsername(String username) {
        return userRepository.findUserByUsername(username).stream().map(user -> userConverter.convertToDto(user)).collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(CreateUserRequest createUserRequest) {

        Set<Role> roles = new HashSet<>(Arrays.asList(roleRepository.findByCode(RoleName.USER).get()));

        User user = new User(createUserRequest.getUsername(), encoder.encode(createUserRequest.getPassword()), roles);

        this.userRepository.save(user);
    }

}
