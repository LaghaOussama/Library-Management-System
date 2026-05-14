package com.Library_Management_System.service.impl;


import com.Library_Management_System.exception.GenreException;
import com.Library_Management_System.mapper.GenreMapper;
import com.Library_Management_System.modal.Genre;
import com.Library_Management_System.payload.dto.GenreDTO;
import com.Library_Management_System.repository.GenreRepository;
import com.Library_Management_System.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
        //return genreRepository.save(genre);

        Genre genre = genreMapper.toEntity(genreDTO);
        Genre savedGenre =genreRepository.save(genre);
        return genreMapper.toDTO(savedGenre);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(genreMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDTO getGenreById(Long genreId) throws GenreException {
        Genre genre= genreRepository.findById(genreId).orElseThrow(
                ()-> new GenreException("genre not found"));
        return genreMapper.toDTO(genre);
    }

    @Override
    public GenreDTO updateGenre(Long id, GenreDTO genre) {
        return null;
    }

    @Override
    public void deleteGenre(Long id) {

    }

    @Override
    public void hardDeleteGenre(Long genreId) {

    }

    @Override
    public List<GenreDTO> getAllActiveGenreWithSubGenres() {
        return List.of();
    }

    @Override
    public List<GenreDTO> getTopLevelGenres() {
        return List.of();
    }

    @Override
    public long getTotalActiveGenres() {
        return 0;
    }

    @Override
    public long getBookCountByGenre(Long genreId) {
        return 0;
    }

}
