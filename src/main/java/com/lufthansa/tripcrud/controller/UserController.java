package com.lufthansa.tripcrud.controller;

import com.lufthansa.tripcrud.dto.ResponseMsg;
import com.lufthansa.tripcrud.dto.UserDto;
import com.lufthansa.tripcrud.entity.User;
import com.lufthansa.tripcrud.repository.UserRepository;
import com.lufthansa.tripcrud.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {


    public UserRepository userRepository;

    public UserService userService;

    private PasswordEncoder encoder;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.encoder = encoder;
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

//    @GetMapping("/{username}")
//    public List<UserDto> findByUsername(@PathVariable String username){
//        return userService.findByUsername(username);
//    }

//    @PostMapping("/{username}")
//    public List<UserDto> username(@PathVariable String username){
//        return userService.findByUsername(username);
//    }

//    @GetMapping("/list")
//    public List<User> findAllUsers() {
//            return userService.findAll();
//    }

    @PostMapping("/createUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto createUser){
        if (userRepository.existsByUsername(createUser.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMsg("Error: User with this username already exists!"));
        }
        this.userService.createUser(createUser.getUsername(), encoder.encode(createUser.getPassword()), createUser.getRole());
        return ResponseEntity.ok(new ResponseMsg("User added successfully!"));
    }
}
