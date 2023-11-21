package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.exception.BookNotFoundException;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.model.BookData;
import com.bridgelabz.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService{

/*     (insert, getAll, getByID, delete, searchByBookName, updateBookByID, sortingAsc,
       sortingDsc, updateQuantity)
*/

        @Autowired
        BookRepository bookRepository;

        @Override
        public ResponseEntity<ResponseDTO> getAllBooks(){

            if (!bookRepository.findAll().isEmpty()){

                ResponseDTO responseDTO = new ResponseDTO("Get Call Successful", bookRepository.findAll());

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);

            }else{

                throw new UserException("No data found.");

            }

        }

        @Override
        public ResponseEntity<ResponseDTO> getBookById(int bookId){

            if (bookRepository.existsById(bookId)){

                ResponseDTO responseDTO = new ResponseDTO("Get call successful", bookRepository.findById(bookId).get());

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);

            }else {

                throw new BookNotFoundException("Book not found. Check UserId.");

            }

        }

        @Override
        public ResponseEntity<ResponseDTO> searchByBookName(String bookName){

            BookData bookData = bookRepository.getBookByBookName(bookName);

            if (bookData!=null){

                ResponseDTO responseDTO = new ResponseDTO("Get call successful", bookData);

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);

            }else {

                throw new BookNotFoundException("Book Not found. Book Name Incorrect.");

            }

        }

        @Override
        public ResponseEntity<ResponseDTO> insertBookDetails(BookDTO bookDto){

            BookData bookData = new BookData(bookDto);

            bookRepository.save(bookData);

            ResponseDTO responseDTO = new ResponseDTO("New book added", bookData);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }

        @Override
        public ResponseEntity<ResponseDTO> updateBookDataById(int bookId, BookDTO bookDTO){

            if (bookRepository.existsById(bookId)){

                BookData bookData = new BookData(bookDTO);

                bookData.setBookId(bookId);

                bookRepository.save(bookData);

                ResponseDTO responseDTO = new ResponseDTO("Updated Book by Id: " + bookId, bookData);

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);

            }else {

                throw new BookNotFoundException("Book not found. Check bookId.");

            }

        }

        @Override
        public ResponseEntity<ResponseDTO> updateQuantityOfBook(int bookId, int quantity){

            if(bookRepository.existsById(bookId)){

                BookData bookData = bookRepository.findById(bookId).get();

                bookData.setQuantity(quantity);

                bookRepository.save(bookData);

                ResponseDTO responseDTO = new ResponseDTO("Book Quantity Updated", bookData );

                return new ResponseEntity<>(responseDTO,HttpStatus.OK);

            }else {

                throw new BookNotFoundException("No book found. Check bookId.");

            }

        }

        @Override
        public ResponseEntity<ResponseDTO> sortBookDataInAsc(){

            List<BookData> bookDataList = bookRepository.findAll(Sort.by(Sort.Direction.ASC, "bookName"));

            ResponseDTO responseDTO = new ResponseDTO("Book Sorted in Ascending order.", bookDataList);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }

        @Override
        public ResponseEntity<ResponseDTO> sortBookDataInDesc(){

            List<BookData> bookDataList = bookRepository.findAll(Sort.by(Sort.Direction.DESC, "bookName"));

            ResponseDTO responseDTO = new ResponseDTO("Book Sorted in Descending order.", bookDataList);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }

        @Override
        public ResponseEntity<ResponseDTO> deleteById(int bookId){

            if (bookRepository.existsById(bookId)){

                bookRepository.deleteById(bookId);

                ResponseDTO responseDTO = new ResponseDTO("Book Deleted.", false);

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);

            }else {

                throw new BookNotFoundException("Book not found. Check UserId.");

            }

        }

}
