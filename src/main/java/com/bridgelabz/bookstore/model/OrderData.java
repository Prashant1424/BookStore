package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.OrderDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int orderID;

    public LocalDate date;
    public int totalPrice;
    public int quantity;
    public String address;
    public int userID;
    public int bookID;
    public boolean cancel;

    public OrderData (OrderDTO orderDTO){
        this.date = orderDTO.date;
        this.totalPrice=orderDTO.totalPrice;
        this.quantity=orderDTO.quantity;
        this.address=orderDTO.address;
        this.userID=orderDTO.userID;
        this.bookID=orderDTO.bookID;
    }

}
