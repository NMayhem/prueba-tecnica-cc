package com.aidaml.cc.demo.exception;

public class BadCredentialsException extends AuthenticationException {
    
    public BadCredentialsException(String message) {
        super(message);
    }

    public BadCredentialsException() {
        super("The password is incorrect.");
    }

}
