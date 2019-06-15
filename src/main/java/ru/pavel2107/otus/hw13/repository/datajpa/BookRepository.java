package ru.pavel2107.otus.hw13.repository.datajpa;

import org.springframework.data.repository.CrudRepository;
import ru.pavel2107.otus.hw13.domain.Book;

import java.util.List;


public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByIsbn( String Isbn);
    List<Book> findBookByName( String name);
    List<Book> findBookByAuthorId( Long id);
    List<Book> findBookByGenreId( Long id);
}
