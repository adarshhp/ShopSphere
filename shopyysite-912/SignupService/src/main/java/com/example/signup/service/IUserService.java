package com.example.signup.service;

import java.util.List;

import com.example.signup.dto.UserDTO;
import com.example.signup.model.UserDetails;
import com.example.signup.model.response.LoginResponse;
import com.example.signup.model.response.SignInResponse;

public interface IUserService {
	public SignInResponse SignIn(UserDetails userdetails);
	public LoginResponse Login(UserDTO usedto);
	public List<UserDetails>GetUsers();

}
