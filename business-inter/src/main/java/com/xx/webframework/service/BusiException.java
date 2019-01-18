package com.xx.webframework.service;

public class BusiException extends RuntimeException {

    public BusiException(){
        super();
    }

    public BusiException(String message){
        super(message);
    }

    public BusiException(String message, Throwable throwable){
        super(message,throwable);
    }
}
