package com.lufthansa.tripcrud.services.impl;


import com.lufthansa.tripcrud.converter.UserConverter;
import com.lufthansa.tripcrud.dto.UserDto;
import com.lufthansa.tripcrud.entity.Role;
import com.lufthansa.tripcrud.entity.User;
import com.lufthansa.tripcrud.repository.UserRepository;
import com.lufthansa.tripcrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public List<UserDto> findByUsername(String username) {
        return userRepository.findUserByUsername(username).stream()
                .map(user -> userConverter.convertToDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void createUser(String username, String password, Set<Role> roles) {
        User user = new User(username, password, roles);
        this.userRepository.save(user);
    }

}
