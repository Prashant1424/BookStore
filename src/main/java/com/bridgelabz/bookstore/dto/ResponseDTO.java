package com.bridgelabz.bookstore.dto;

public class ResponseDTO {
    public String msg;
    public Object data;

    public ResponseDTO(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }
}
