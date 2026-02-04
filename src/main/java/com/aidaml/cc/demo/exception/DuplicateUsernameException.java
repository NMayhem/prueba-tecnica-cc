package com.aidaml.cc.demo.exception;

public class DuplicateUsernameException extends RuntimeException {
    
    public DuplicateUsernameException(String message) {
        super(message);
    }

    public DuplicateUsernameException() {
        super("The tax ID or username is already recorded.");
    }

}
