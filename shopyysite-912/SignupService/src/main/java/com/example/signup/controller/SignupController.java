package com.example.signup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.signup.dto.UserDTO;
import com.example.signup.model.UserDetails;
import com.example.signup.model.payload.UserPayload;
import com.example.signup.model.response.LoginResponse;
import com.example.signup.model.response.SignInResponse;
import com.example.signup.service.IUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class SignupController {
	@Autowired
	private  IUserService service;
	

	@PostMapping("/signup")
	public SignInResponse signUp(@RequestBody UserPayload userPayload) {
	    UserDetails user = new UserDetails();
	    user.setUserName(userPayload.getUserName());
	    user.setEmail(userPayload.getEmail());
	    user.setPassword(userPayload.getPassword());
	    
	    // Set a default or derived value for userType
	    user.setUserType(1); // default type

	    return service.SignIn(user);
	}

	
	@PostMapping("/login")
	public LoginResponse Login(@RequestBody UserDTO usedto) {
		return service.Login(usedto);
	}
	
	@GetMapping("/getusers")
	public List<UserDetails>GetUsers(){
		return service.GetUsers();
	}
	
	@PostMapping("/createuser")
	public SignInResponse SignIn(@RequestBody UserDetails userdetails) {
		
		 UserDetails user = new UserDetails();
		    user.setUserName(userdetails.getUserName());
		    user.setEmail(userdetails.getEmail());
		    user.setPassword(userdetails.getPassword());
		    
		    // Set a default or derived value for userType
		    user.setUserType(userdetails.getUserType()); // default type

		return service.SignIn(user);
	}
}
