package com.kitkamseng.votebackend.exception;

public class UserNameNotFoundException extends RuntimeException{
    public  UserNameNotFoundException(String username){
        super("Could not find user by username: " + username);
    }
}
