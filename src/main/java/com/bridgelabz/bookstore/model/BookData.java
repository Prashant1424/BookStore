package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.BookDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BookData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int bookId;

    public String bookName;
    public String authorName;
    public String bookDescription;
    public String bookImg;
    public int price;
    public int quantity;

    public BookData (BookDTO bookDTO){
        this.bookName = bookDTO.bookName;
        this.authorName = bookDTO.authorName;
        this.bookDescription = bookDTO.bookDescription;
        this.bookImg = bookDTO.bookImg;
        this.price = bookDTO.price;
        this.quantity = bookDTO.quantity;
    }

}