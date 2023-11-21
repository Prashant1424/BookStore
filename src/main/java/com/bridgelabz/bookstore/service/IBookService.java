package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IBookService {

    ResponseEntity<ResponseDTO> getAllBooks();
    ResponseEntity<ResponseDTO> getBookById(int bookId);
    ResponseEntity<ResponseDTO> searchByBookName(String bookName);
    ResponseEntity<ResponseDTO> insertBookDetails(BookDTO bookDTO);
    ResponseEntity<ResponseDTO> updateBookDataById(int bookId, BookDTO bookDTO);
    ResponseEntity<ResponseDTO> updateQuantityOfBook(int bookId, int quantity);
    ResponseEntity<ResponseDTO> sortBookDataInAsc();
    ResponseEntity<ResponseDTO> sortBookDataInDesc();
    ResponseEntity<ResponseDTO> deleteById(int bookId);

}
