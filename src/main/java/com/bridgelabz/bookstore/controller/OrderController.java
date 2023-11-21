package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll(){
        return orderService.getAllOrderData();
    }

    @GetMapping("/getById/{orderId}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable int orderId){
        return orderService.getOrderDataById(orderId);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insert(@Valid @RequestBody OrderDTO orderDTO){
        return orderService.insertOrderData(orderDTO);
    }

    @PutMapping("/updateById/{orderId}")
    public ResponseEntity<ResponseDTO> updateById(@PathVariable int orderId,@Valid @RequestBody OrderDTO orderDTO){
        return orderService.updateOrderById(orderId, orderDTO);
    }

    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable int orderId){
        return orderService.cancelOrderById(orderId);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable int orderId){
        return orderService.deleteById(orderId);
    }

}