package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IOrderService {
    ResponseEntity<ResponseDTO> getAllOrderData();
    ResponseEntity<ResponseDTO> getOrderDataById(int orderId);
    ResponseEntity<ResponseDTO> insertOrderData(OrderDTO orderDTO);
    ResponseEntity<ResponseDTO> updateOrderById(int orderId, OrderDTO orderDTO);
    ResponseEntity<ResponseDTO> cancelOrderById(int orderId);
    ResponseEntity<ResponseDTO> deleteById(int orderId);

}
