package ru.pavel2107.otus.hw13.service;

import ru.pavel2107.otus.hw13.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre save( Genre genre);
    void delete( Long ID);
    Genre find(Long ID);
    List<Genre> findAll();

}
