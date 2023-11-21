package com.bridgelabz.bookstore.dto;

import jakarta.validation.constraints.*;

public class BookDTO {

    @Size(min = 3, message = "Name should have more than 3 characters")
    public String bookName;
    @Pattern(regexp = "[A-Z][a-z]{2,}", message = "First letter should be capital, with min 3 characters")
    public  String  authorName;
    @Size(min = 3, max = 25)
    public String bookDescription;
    @NotEmpty
    public String bookImg;
    @Min(1)
    public int price;

    @Min(1)
    public int quantity;

}
