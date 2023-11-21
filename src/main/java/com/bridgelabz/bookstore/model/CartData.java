package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.CartDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CartData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int cartID;

    public int userID;
    public int bookID;
    public int quantity;

    public CartData (CartDTO cartDTO){
        this.userID = cartDTO.userID;
        this.bookID = cartDTO.bookID;
        this.quantity = cartDTO.quantity;
    }

}
