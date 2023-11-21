package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.service.ICartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")

public class CartController {

    @Autowired
    ICartService cartService;


    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllCartData(){
        return cartService.getAllCartData();
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insert(@Valid @RequestBody CartDTO cartDTO){
        return cartService.insertDataIntoCart(cartDTO);
    }
    @PutMapping("/updateById/{cartId}")
    public ResponseEntity<ResponseDTO> updateCart(@PathVariable int cartId, @Valid @RequestBody CartDTO cartDTO){
        return cartService.updateCartById(cartId, cartDTO);
    }

    @PutMapping("/updateQuantity/{cartId}")
    public ResponseEntity<ResponseDTO> updateQuan(@PathVariable int cartId,@RequestParam int quantity){
        return cartService.updateQuantityInCart(cartId, quantity);
    }

    @DeleteMapping("/deleteById/{cartId}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable int cartId){
        return cartService.deleteById(cartId);
    }
}