package com.relevel.test.controller;

import com.relevel.test.entity.AuthenticationRequest;
import com.relevel.test.payloads.UserInput;
import com.relevel.test.payloads.UserOutput;
import com.relevel.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/user")
	public ResponseEntity<String> addUser(@Valid @RequestBody UserInput userInput) {
//		try {
			this.userService.addUser(userInput);
			return new ResponseEntity<String>("User Created Successfully !!", HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<String>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
//
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserOutput> getUser(@PathVariable Integer userId) {
		UserOutput userOutput = this.userService.getUser(userId);
		return new ResponseEntity<>(userOutput, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			String jwt = this.userService.authenticateUser(authenticationRequest);
			return ResponseEntity.ok(jwt);

		} catch (BadCredentialsException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Incorrect username or password", HttpStatus.BAD_REQUEST);

		}
	}

}
