package com.example.UserService.util;

public class UserNotCreatedException extends RuntimeException {
    public UserNotCreatedException(String msg) {
        super(msg);
    }
}
