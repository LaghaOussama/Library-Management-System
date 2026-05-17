package com.Library_Management_System.mapper;


import com.Library_Management_System.exception.BookException;
import com.Library_Management_System.modal.Book;
import com.Library_Management_System.modal.Genre;
import com.Library_Management_System.payload.dto.BookDTO;
import com.Library_Management_System.repository.GenreRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper {


    private final GenreRepository genreRepository;

    public BookDTO toDTO(Book book) {
        if(book==null){
            return null;
        }
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .genreId(book.getGenre().getId())
                .genreName(book.getGenre().getName())
                .genreCode(book.getGenre().getCode())
                .publisher(book.getPublisher())
                .publicationDate(book.getPublishDate())
                .language(book.getLanguage())
                .pages(book.getPages())
                .description(book.getDescription())
                .totalCopies(book.getTotalCopies())
                .availableCopies(book.getAvailableCopies())
                .price(book.getPrice())
                .coverImageUrl(book.getCoverImageUrl())
                .active(book.getActive())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    public Book toEntity(BookDTO dto) throws BookException {
        if(dto==null){
            return null;
        }

        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());

        if(dto.getGenreId()!=null){
            Genre genre =genreRepository.findById(dto.getGenreId())
                    .orElseThrow(()->new BookException("Genre with ID "+dto.getGenreId()
                            +" notfound"));
                    book.setGenre(genre);
        }
        book.setPublisher(dto.getPublisher());
        book.setPublishDate(dto.getPublicationDate());
        book.setLanguage(dto.getLanguage());
        book.setPages(dto.getPages());
        book.setDescription(dto.getDescription());
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getAvailableCopies());
        book.setPrice(dto.getPrice());
        book.setCoverImageUrl(dto.getCoverImageUrl());
        book.setActive(true);
        return book;
    }

    public void updateEntityFromDTO(BookDTO dto, Book book) throws BookException {
        if(dto==null || book==null){
            return;
        }

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        if(dto.getGenreId()!=null){
            Genre genre =genreRepository.findById(dto.getGenreId())
                    .orElseThrow(()->new BookException("Genre with ID "+dto.getGenreId()
                    +" notfound"));
            book.setGenre(genre);
        }
        book.setPublisher(dto.getPublisher());
        book.setPublishDate(dto.getPublicationDate());
        book.setLanguage(dto.getLanguage());
        book.setPages(dto.getPages());
        book.setDescription(dto.getDescription());
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getAvailableCopies());
        book.setPrice(dto.getPrice());
        book.setCoverImageUrl(dto.getCoverImageUrl());
        if(dto.getActive()!=null){
            book.setActive(dto.getActive());
        }
    }
}
