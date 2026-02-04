package com.aidaml.cc.demo.exception;

public class AuthenticationException extends RuntimeException {
    
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException() {
        super("Unauthorized.");
    }

}
