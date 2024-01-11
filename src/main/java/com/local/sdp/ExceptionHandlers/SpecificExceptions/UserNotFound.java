package com.local.sdp.ExceptionHandlers.SpecificExceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message) {
        super(message);
    }
}
