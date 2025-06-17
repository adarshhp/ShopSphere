package com.example.signup.service;


import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.signup.dto.UserDTO;
import com.example.signup.jwt.JwtUtil;
import com.example.signup.model.UserDetails;
import com.example.signup.model.response.LoginResponse;
import com.example.signup.model.response.SignInResponse;
import com.example.signup.repository.SignupRepository;

@Service
public class UserService implements IUserService {

    private final SignupRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    
    public UserService(SignupRepository userRepository,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtUtil = jwtUtil;
    }

    @Override
    public SignInResponse SignIn(UserDetails userDetails) {
        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        SignInResponse response = new SignInResponse();

        UserDetails savedUser = userRepository.save(userDetails);

        if (savedUser != null && savedUser.getUserId() != null) {
            response.setMessage("Signup success");
            response.setStatusCode(200);
        } else {
            response.setMessage("Signup failed");
            response.setStatusCode(400);
        }

        return response;
    }

    
    @Override
    public LoginResponse Login(UserDTO userDto) {
        UserDetails user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        LoginResponse obj=new LoginResponse();
        if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getEmail());
            System.out.println("JWT Token: " + token); // or return it in response
            obj.setJwt(token);
            obj.setStatusCode(200);
            obj.setMessage("Logged In");
            
            return obj;
        }else {
        	obj.setJwt("");
            obj.setStatusCode(400);
            obj.setMessage("Invalid credentials");
            return obj;
        }
    }
    
    @Override
	public List<UserDetails>GetUsers(){
		return userRepository.GetUsers();
	}


}
