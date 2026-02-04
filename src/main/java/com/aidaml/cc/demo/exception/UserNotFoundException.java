package com.aidaml.cc.demo.exception;

public class UserNotFoundException extends AuthenticationException {
    
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("User not found");
    }

}
