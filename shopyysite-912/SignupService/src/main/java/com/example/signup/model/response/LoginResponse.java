package com.example.signup.model.response;


public class LoginResponse {

    private int statusCode;
    private String message;  // ✅ use lowercase 'message'
    private String jwt;

    // Getters and Setters
    public int getStatusCode() {
        return statusCode;
    }

   
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

	public void setStatusCode(int statusCode2) {
        this.statusCode = statusCode2;
	}
}

