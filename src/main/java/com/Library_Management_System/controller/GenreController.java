package com.Library_Management_System.controller;


import com.Library_Management_System.exception.GenreException;
import com.Library_Management_System.modal.Genre;
import com.Library_Management_System.payload.dto.GenreDTO;
import com.Library_Management_System.payload.response.ApiResponse;
import com.Library_Management_System.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/create")
    public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genre) {
        GenreDTO createdGenre=genreService.createGenre(genre);
        return ResponseEntity.ok(createdGenre);
    }

    @GetMapping()
    public ResponseEntity<?> getAllGenres(@RequestBody GenreDTO genre) {
        List<GenreDTO> genres=genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{genreId}")
    public ResponseEntity<?> getGenreById(@PathVariable("genreId") Long genreId) throws GenreException {
        GenreDTO genres=genreService.getGenreById(genreId);
        return ResponseEntity.ok(genres);
    }

    @PutMapping("/{genreId}")
    public ResponseEntity<?> updateGenre(@PathVariable("genreId") Long genreId,
                                         @RequestBody GenreDTO genre) throws GenreException {
        GenreDTO genres=genreService.updateGenre(genreId,genre);
        return ResponseEntity.ok(genres);
    }

    @DeleteMapping("/{genreId}")
    public ResponseEntity<?> deleteGenre(@PathVariable("genreId") Long genreId) throws GenreException {
        genreService.deleteGenre(genreId);
        ApiResponse response =new ApiResponse
                ("Gnre Deleted with Success -soft delete",true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{genreId}/hard")
    public ResponseEntity<?> hardDeleteGenre(@PathVariable("genreId") Long genreId) throws GenreException {
        genreService.deleteGenre(genreId);
        ApiResponse response =new ApiResponse
                ("Genre Deleted with Success -soft delete",true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/top-level")
    public ResponseEntity<?> getTopLevelGenres()  {
        List<GenreDTO> genres=genreService.getTopLevelGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getTotalActiveGenres()  {
        Long genres=genreService.getTotalActiveGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}/book-count")
    public ResponseEntity<?> getBookCountByGenres(@PathVariable Long id)  {
        Long count =  genreService.getBookCountByGenre(id);
        return ResponseEntity.ok(count);
    }


}
