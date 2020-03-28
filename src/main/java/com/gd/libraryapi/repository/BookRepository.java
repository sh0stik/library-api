package com.gd.libraryapi.repository;

import com.gd.libraryapi.model.Book;

import java.util.List;

/**
 * This interface is a data access object class for entity {@link Book} with CRUD operations
 */
public interface BookRepository {

    Book save(Book book);

    void deleteById(Long id);

    Book getOne(Long id);

    List<Book> findAll();

}
