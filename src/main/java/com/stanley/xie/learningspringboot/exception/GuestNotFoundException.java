package com.stanley.xie.learningspringboot.exception;

public class GuestNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Guest not found";
    }
}
