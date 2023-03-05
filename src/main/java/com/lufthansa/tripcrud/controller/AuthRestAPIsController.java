package com.lufthansa.tripcrud.controller;

import com.lufthansa.tripcrud.dto.*;
import com.lufthansa.tripcrud.entity.Role;
import com.lufthansa.tripcrud.entity.RoleName;
import com.lufthansa.tripcrud.entity.User;
import com.lufthansa.tripcrud.security.jwt.JwtProvider;
import com.lufthansa.tripcrud.repository.RoleRepository;
import com.lufthansa.tripcrud.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class AuthRestAPIsController {

	AuthenticationManager authenticationManager;
	UserRepository userRepository;
	RoleRepository roleRepository;
	PasswordEncoder encoder;
	JwtProvider jwtProvider;


	@Autowired
	public AuthRestAPIsController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtProvider jwtProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.jwtProvider = jwtProvider;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwtToken(authentication);
		return ResponseEntity.ok(new JwtResponse(jwt));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<String>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));
		Set<Role> roles = new HashSet<>();

		roles.add(roleRepository.findByCode(RoleName.USER).get());
		user.setRole(roles);
		userRepository.save(user);
		return ResponseEntity.ok().body("User registered successfully!");
	}

}