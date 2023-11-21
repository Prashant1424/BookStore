package com.bridgelabz.bookstore.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String msg){
        super(msg);
    }
}
