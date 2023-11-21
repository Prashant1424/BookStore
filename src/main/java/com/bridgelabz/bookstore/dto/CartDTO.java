package com.bridgelabz.bookstore.dto;

import jakarta.validation.constraints.Min;

public class CartDTO {
    @Min(1)
    public int userID;
    @Min(1)
    public int bookID;
    @Min(1)
    public int quantity;
}
