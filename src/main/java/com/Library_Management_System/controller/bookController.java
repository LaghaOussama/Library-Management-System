package com.Library_Management_System.controller;


import com.Library_Management_System.exception.BookException;
import com.Library_Management_System.payload.dto.BookDTO;
import com.Library_Management_System.payload.request.BookSearchRequest;
import com.Library_Management_System.payload.response.ApiResponse;
import com.Library_Management_System.payload.response.PageResponse;
import com.Library_Management_System.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class bookController {

    private final BookService bookService;
    @PostMapping
    public ResponseEntity<BookDTO>createBook(
            @Valid @RequestBody BookDTO bookDTO) throws BookException {
        BookDTO cretedBook=bookService.createBook(bookDTO);
        return ResponseEntity.ok(cretedBook);
    }

    @PostMapping("/bulk")
    public ResponseEntity<?>createBookBulk(
            @Valid @RequestBody List<BookDTO> bookDTOs) throws BookException {
        List<BookDTO> cretedBooks=bookService.createBookBulk(bookDTOs);
        return ResponseEntity.ok(cretedBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO>getBookById(
            @PathVariable("id") Long id) throws BookException {
        BookDTO cretedBook=bookService.getBookById(id);
        return ResponseEntity.ok(cretedBook);
    }

    /*@GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable("isbn") String isbn) throws BookException {
        BookDTO cretedBook=bookService.getBookByISBN(isbn);
        return ResponseEntity.ok(cretedBook);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id,
                                              @RequestBody BookDTO bookDTO)
            throws BookException {

            BookDTO updatedBook=bookService.updateBook(id,bookDTO);
            return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBook(
            @PathVariable Long id) throws BookException {
        bookService.deleteBook(id);
        return ResponseEntity.ok(new ApiResponse("Book deleted successfully",true));
    }

    @DeleteMapping("/{id}/permanent")
    public ResponseEntity<ApiResponse> hardDeleteBook(
            @PathVariable Long id) throws BookException {
        bookService.hardDeleteBook(id);
        return ResponseEntity.ok(new ApiResponse("Book permanently deletes",true));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookDTO>>searchBooks(
            @RequestParam(required = false)Long genreId,
            @RequestParam(required = false,defaultValue = "false")Boolean availableOnly,
            @RequestParam(defaultValue = "true") boolean activeOnly,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection
    ){
        BookSearchRequest searchRequest=new BookSearchRequest();
        searchRequest.setGenreId(genreId);
        searchRequest.setAvailableOnly(availableOnly);
        searchRequest.setPage(page);
        searchRequest.setSize(size);
        searchRequest.setSortBy(sortBy);
        searchRequest.setSortDirection(sortDirection);

        PageResponse<BookDTO> books=bookService.searchBooksWithFilters(searchRequest);
        return ResponseEntity.ok(books);
    }



    @PostMapping("/search")
    public ResponseEntity<PageResponse<BookDTO>> advancedSearch(
            @RequestBody BookSearchRequest searchRequest){
        PageResponse<BookDTO> books=bookService.searchBooksWithFilters(searchRequest);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/state")
    public ResponseEntity<BookStatsResponse> getBookStats(){
        long totalActive =bookService.getTotalActiveBooks();
        long totalAvailable =bookService.getTotalAvailableBooks();
        BookStatsResponse statsResponse=new BookStatsResponse(totalActive,totalAvailable);
        return ResponseEntity.ok(statsResponse);
    }

    public static class BookStatsResponse{
        public long totalActiveBooks;
        public long totalAvailableBooks;

        public BookStatsResponse(long totalActiveBooks, long totalAvailableBooks) {
            this.totalActiveBooks = totalActiveBooks;
            this.totalAvailableBooks = totalAvailableBooks;
        }
    }
}
