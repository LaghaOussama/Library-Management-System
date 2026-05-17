package com.Library_Management_System.controller;


import com.Library_Management_System.exception.BookException;
import com.Library_Management_System.payload.dto.BookDTO;
import com.Library_Management_System.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class bookController {

    private final BookService bookService;
    @PostMapping
    public ResponseEntity<BookDTO>createBook(@Valid @RequestBody BookDTO bookDTO) throws BookException {
        BookDTO cretedBook=bookService.createBook(bookDTO);
        return ResponseEntity.ok(cretedBook);
    }

    @PostMapping
    public ResponseEntity<?>createBookBulk(
            @Valid @RequestBody List<BookDTO> bookDTOs) throws BookException {
        List<BookDTO> cretedBooks=bookService.createBookBulk(bookDTOs);
        return ResponseEntity.ok(cretedBooks);
    }
}
