package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.email.EmailSenderService;
import com.bridgelabz.bookstore.exception.OrderNotFoundException;
import com.bridgelabz.bookstore.model.OrderData;
import com.bridgelabz.bookstore.model.UserData;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{

//    (insert,getall,getbyID,delete,updatebyId, cancelOrder)(JMS)

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public ResponseEntity<ResponseDTO> getAllOrderData(){

        ResponseDTO responseDTO = new ResponseDTO("Get call successful", orderRepository.findAll());

        return  new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseDTO> getOrderDataById(int orderId){

        if (orderRepository.existsById(orderId)){

            ResponseDTO responseDTO = new ResponseDTO("Get call by Id is successful", orderRepository.findById(orderId).get());

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }else {

            throw new OrderNotFoundException("Order not found. Check orderId.");

        }

    }

    @Override
    public ResponseEntity<ResponseDTO> insertOrderData(OrderDTO orderDTO){

        OrderData orderData = new OrderData(orderDTO);

        orderData.setDate(LocalDate.now());

        orderRepository.save(orderData);

        Optional<UserData> userData = userRepository.findById(orderData.getUserID());

        emailSenderService.sendEmail(userData.get().getEmail(), "Order placed.", "The order has been placed successfully for the books.");

        ResponseDTO responseDTO = new ResponseDTO("Post call successful",orderData);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseDTO> updateOrderById(int orderId, OrderDTO orderDTO){

        if (orderRepository.existsById(orderId)){

            OrderData orderData = new OrderData(orderDTO);

            orderData.setOrderID(orderId);

            orderData.setDate(LocalDate.now());

            orderRepository.save(orderData);

            Optional<UserData> user = userRepository.findById(orderData.getUserID());

            emailSenderService.sendEmail(user.get().getEmail(), "Order Updated.", "The orderData has been updated successfully for the books.");

            ResponseDTO responseDTO = new ResponseDTO("Order is updated for Id: " + orderId, orderData);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }else {

            throw new OrderNotFoundException("Order not found. Check orderId.");

        }
    }

    @Override
    public ResponseEntity<ResponseDTO> cancelOrderById(int orderId){

        if(orderRepository.existsById(orderId)){

            OrderData orderData = orderRepository.findById(orderId).get();

            Optional<UserData> userData = userRepository.findById(orderData.getUserID());

            orderData.setOrderID(orderId);

            orderData.setCancel(true);

            orderRepository.save(orderData);

            emailSenderService.sendEmail(userData.get().getEmail(), "Order cancelled.", "The order has been cancelled successfully for the books.");

            ResponseDTO responseDTO = new ResponseDTO("Order cancelled.", orderData);

            return new ResponseEntity<>(responseDTO,HttpStatus.OK);

        }else {

            throw new OrderNotFoundException("Order not found. Check orderId.");

        }
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteById(int orderId){

        if (orderRepository.existsById(orderId)){

            orderRepository.deleteById(orderId);

            ResponseDTO responseDTO = new ResponseDTO("Order Deleted", false);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }else{

            throw new OrderNotFoundException("Order not found. Check orderId.");

        }

    }

}
