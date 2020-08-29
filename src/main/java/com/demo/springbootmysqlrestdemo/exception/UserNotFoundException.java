package com.demo.springbootmysqlrestdemo.exception;

public class UserNotFoundException extends Exception {
    private int user_id;
    public UserNotFoundException(int user_id) {
        super(String.format("User is not found with id : '%s'", user_id));
    }
}