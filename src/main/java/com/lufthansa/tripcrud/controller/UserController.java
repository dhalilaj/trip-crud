package com.lufthansa.tripcrud.controller;

import com.lufthansa.tripcrud.dto.CreateUserRequest;
import com.lufthansa.tripcrud.dto.ResponseMsg;
import com.lufthansa.tripcrud.repository.UserRepository;
import com.lufthansa.tripcrud.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


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

//    @GetMapping("/all")
//    public String allAccess() {
//        return "Public Content.";
//    }

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
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        if (userRepository.existsByUsername(createUserRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new ResponseMsg("Error: User with this username already exists!"));
        }
        this.userService.createUser(createUserRequest);
        return ResponseEntity.ok(new ResponseMsg("User added successfully!"));
    }
}
