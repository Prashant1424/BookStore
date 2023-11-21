package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.exception.CartNotFoundException;
import com.bridgelabz.bookstore.model.CartData;
import com.bridgelabz.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService{

//    (insert,getall,getbyID,updatebyID,updateQuantity,delete)

    @Autowired
    CartRepository cartRepository;

    @Override
    public ResponseEntity<ResponseDTO> getAllCartData(){

        ResponseDTO responseDTO = new ResponseDTO("Get All Cart details.",cartRepository.findAll());

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseDTO> insertDataIntoCart(CartDTO cartDTO){

        CartData cartData = new CartData(cartDTO);

        cartRepository.save(cartData);

        ResponseDTO responseDTO = new ResponseDTO("New Cart created.",cartData);

        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);

    }

    @Override
    public ResponseEntity<ResponseDTO> updateCartById(int cartId, CartDTO cartDTO){

        if (cartRepository.existsById(cartId)){

            CartData cartData = new CartData(cartDTO);

            cartData.setCartID(cartId);

            cartRepository.save(cartData);

            ResponseDTO responseDTO = new ResponseDTO("Get All Cart details.",cartData);

            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED) ;

        }else {

            throw new CartNotFoundException("Cart not found. Check cartId.");

        }

    }

    @Override
    public ResponseEntity<ResponseDTO> updateQuantityInCart(int cartId, int quantity){

        if(cartRepository.existsById(cartId)){

            CartData cartData =cartRepository.findById(cartId).get();

            cartData.setQuantity(quantity);

            cartRepository.save(cartData);

            ResponseDTO responseDTO = new ResponseDTO(" Quantity Updated", cartData );

            return new ResponseEntity<>(responseDTO,HttpStatus.OK);

        }else {

            throw new CartNotFoundException("No cart found. Check cartId.");

        }

    }

    @Override
    public ResponseEntity<ResponseDTO> deleteById(int cartId){

        if (cartRepository.existsById(cartId)){

            cartRepository.deleteById(cartId);

            ResponseDTO responseDTO= new ResponseDTO("Cart Deleted", false);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }else{

            throw new CartNotFoundException("Cart not found. Check cartId.");

        }

    }

}
