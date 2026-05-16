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
    public GenreDTO updateGenre(Long id, GenreDTO genreDTO) throws GenreException {

        Genre existingGenre = genreRepository.findById(id).orElseThrow(
                ()-> new GenreException("genre not found")
        );
        genreMapper.updateEntityFronDTO(genreDTO,existingGenre);

        Genre updatedGenre=genreRepository.save(existingGenre);

        return genreMapper.toDTO(updatedGenre);
    }

    @Override
    public void deleteGenre(Long id) throws GenreException {
        Genre existingGenre = genreRepository.findById(id).orElseThrow(
                ()-> new GenreException("genre not found")
        );
        existingGenre.setActive(false);
    }

    @Override
    public void hardDeleteGenre(Long genreId) throws GenreException {
        Genre existingGenre = genreRepository.findById(genreId).orElseThrow(
                ()-> new GenreException("genre not found")
        );
        genreRepository.delete(existingGenre);
    }

    @Override
    public List<GenreDTO> getAllActiveGenreWithSubGenres() {
        List<Genre> topLevelGenres=genreRepository
                .findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();
        return genreMapper.toDTOList(topLevelGenres);
    }

    @Override
    public List<GenreDTO> getTopLevelGenres() {
        List<Genre> topLevelGenres=genreRepository
                .findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();
        return genreMapper.toDTOList(topLevelGenres);
    }

    @Override
    public long getTotalActiveGenres() {

        return genreRepository.countByActiveTrue();
    }

    @Override
    public long getBookCountByGenre(Long genreId) {

        return 0;
    }

}
