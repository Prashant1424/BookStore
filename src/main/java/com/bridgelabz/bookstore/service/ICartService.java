package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ICartService {

    ResponseEntity<ResponseDTO> getAllCartData();
    ResponseEntity<ResponseDTO> insertDataIntoCart(CartDTO cartDTO);
    ResponseEntity<ResponseDTO> updateCartById(int cartId, CartDTO cartDto);
    ResponseEntity<ResponseDTO> updateQuantityInCart(int cartId, int quantity);
    ResponseEntity<ResponseDTO> deleteById(int cartId);

}
