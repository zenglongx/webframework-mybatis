package com.xx.webframework.service;

public class UserNotFoundException extends BusiException {

    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(String message, Throwable throwable){
        super(message,throwable);
    }
}
