package com.bridgelabz.bookstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public class OrderDTO {
    public LocalDate date;
    @Min(1)
    public int totalPrice;
    @Min(1)
    public int quantity;
    @NotEmpty
    public String address;
    @Min(1)
    public int userID;
    @Min(1)
    public int bookID;
}
