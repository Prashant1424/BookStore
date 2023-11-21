package com.bridgelabz.bookstore.exception;

public class PasswordNotFoundException extends RuntimeException{

    public PasswordNotFoundException(String msg){
        super(msg);
    }
}
