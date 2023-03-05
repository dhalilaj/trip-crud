package com.lufthansa.tripcrud.converter;

import com.lufthansa.tripcrud.dto.UserDto;
import com.lufthansa.tripcrud.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {
    public UserDto convertToDto(User user) {
        UserDto convertedUser = new UserDto();
        convertedUser.setUsername(user.getUsername());
        convertedUser.setPassword(user.getPassword());
        convertedUser.setRole(user.getRole());
        return convertedUser;
    }
}
