package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.service.IBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookService bookService;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/getById/{bookId}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable int bookId){
        return bookService.getBookById(bookId);
    }

    @GetMapping("/searchByBookName")
    public ResponseEntity<ResponseDTO> searchByBookName(@RequestParam String bookName){
        return bookService.searchByBookName(bookName);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insert(@Valid @RequestBody BookDTO bookDTO){
        return bookService.insertBookDetails(bookDTO);
    }

    @PutMapping("/updateBook/{bookId}")
    public ResponseEntity<ResponseDTO> updateBookById(@PathVariable int bookId, @Valid @RequestBody BookDTO bookDTO){
        return bookService.updateBookDataById(bookId, bookDTO);
    }

    @PutMapping("/updateQuantity/{bookId}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable int bookId, @RequestParam int quantity){
        return bookService.updateQuantityOfBook(bookId, quantity);
    }

    @GetMapping("/sortAsc")
    public ResponseEntity<ResponseDTO> sortAsc(){
        return bookService.sortBookDataInAsc();
    }

    @GetMapping("/sortDesc")
    public ResponseEntity<ResponseDTO> sortDesc(){
        return bookService.sortBookDataInDesc();
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable int bookId){
        return bookService.deleteById(bookId);
    }

}