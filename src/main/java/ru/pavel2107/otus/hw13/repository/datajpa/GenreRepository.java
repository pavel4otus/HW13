package ru.pavel2107.otus.hw13.repository.datajpa;

import org.springframework.data.repository.CrudRepository;
import ru.pavel2107.otus.hw13.domain.Genre;


public interface GenreRepository extends CrudRepository<Genre, Long> {

}

