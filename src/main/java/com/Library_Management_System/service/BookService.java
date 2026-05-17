package com.Library_Management_System.service;

import com.Library_Management_System.exception.BookException;
import com.Library_Management_System.payload.dto.BookDTO;
import com.Library_Management_System.payload.request.BookSearchRequest;
import com.Library_Management_System.payload.response.PageResponse;

import java.util.List;

public interface BookService {


    BookDTO createBook(BookDTO bookDTO) throws BookException;
    List<BookDTO> createBookBulk(List<BookDTO> bookDTOs) throws BookException;
    BookDTO getBookById(Long bookId) throws BookException;
    BookDTO getBookByISBN(String isbn) throws BookException;
    BookDTO updateBook(Long bookId,BookDTO bookDTO) throws BookException;
    void deleteBook(Long bookId) throws BookException;
    void hardDeleteBoook(Long bookId) throws BookException;

    PageResponse<BookDTO> searchBooksWithFilters(BookSearchRequest searchRequest);

    long getTotalActiveBooks();
    long getTotalAvailableBooks();




}

