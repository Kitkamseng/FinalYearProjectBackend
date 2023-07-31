package com.kitkamseng.votebackend.controller;

public class LoginRequest {

    private String username;
    private String password;

    // Default constructor (required for JSON deserialization)
    public LoginRequest() {}

    // Constructor
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters (required for JSON serialization/deserialization)
    // You can generate these using your IDE or write them manually

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
