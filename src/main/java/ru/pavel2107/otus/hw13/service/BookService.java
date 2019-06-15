package ru.pavel2107.otus.hw13.service;

import ru.pavel2107.otus.hw13.domain.Book;

import java.util.List;

public interface BookService {
    Book save(Book author);
    void delete( Long ID);
    Book find( Long ID);

    List<Book> findByName(String name);
    List<Book> findBookByAuthorId( Long authorID);
    Book findByISBN( String ISBN);
    List<Book> findAll();

}


