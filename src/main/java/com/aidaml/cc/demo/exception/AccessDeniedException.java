package com.aidaml.cc.demo.exception;

public class AccessDeniedException extends AuthenticationException {
    
    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException() {
        super("Access Denied.");
    }

}
