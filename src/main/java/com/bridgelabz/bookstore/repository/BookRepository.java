package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<BookData,Integer> {

    @Query("SELECT b FROM BookData b WHERE b.bookName = :n")
    BookData getBookByBookName(@Param("n") String bookName);
}
