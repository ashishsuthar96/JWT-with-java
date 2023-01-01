package com.relevel.test.service;

import com.relevel.test.Repository.UserRepo;
import com.relevel.test.entity.AuthenticationRequest;
import com.relevel.test.entity.User;
import com.relevel.test.exceptions.UnauthorizedException;
import com.relevel.test.exceptions.UserNotFoundException;
import com.relevel.test.payloads.UserInput;
import com.relevel.test.payloads.UserOutput;
import com.relevel.test.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepo userRepo;
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtTokenUtil;
	private final CustomUserDetailService userDetailsService;
	private final ModelMapper mapper;

	@Autowired
	public UserService(PasswordEncoder passwordEncoder, UserRepo userRepo, AuthenticationManager authenticationManager,
			JwtUtil jwtTokenUtil, CustomUserDetailService userDetailsService, ModelMapper mapper) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userRepo = userRepo;
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
		this.mapper = mapper;
	}

	public void addUser(UserInput userInput) {
		User user = mapper.map(userInput, User.class);
		
//		Optional<User> findByEmail = this.userRepo.findByEmail(user.getEmail());
//		if(findByEmail.isEmpty()) {	
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		this.userRepo.save(user);
//		}
//		else {
//			throw new UserNotFoundException("email is already persent");
//		}
	}

	public UserOutput getUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("No User for the given ID !!"));
		return mapper.map(user, UserOutput.class);

	}

	public String authenticateUser(AuthenticationRequest authenticationRequest) throws Exception {

		User user = this.userRepo.findByEmail(authenticationRequest.getUsername())
				.orElseThrow(() -> new UserNotFoundException("User does not exist"));

		if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
			throw new UnauthorizedException("Invalid Password");
		}

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
				authenticationRequest.getPassword()));

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return jwt;

	}
}
