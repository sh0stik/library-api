package com.gd.libraryapi.repository.map;

import com.gd.libraryapi.model.Book;
import com.gd.libraryapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is implementation for {@link BookRepository} using {@code HashMap}
 * with {@code Long} key and {@link Book} value for data storage
 */
@Qualifier("BookRepositoryMapImpl")
@Repository
public class BookRepositoryMapImpl implements BookRepository {
    /**
     * Storage
     */
    private Map<Long, Book> booksById = new HashMap<>();
    private long count;

    /**
     * Save entry to storage with auto generating id
     *
     * @param book - new instance of {@link Book} passed to method
     * @return the book after putting to HashMap
     */
    @Override
    public Book save(Book book) {
        Long id = count++;
        book.setId(id);
        booksById.put(id, book);
        return book;
    }

    /**
     * Deleting by id with using method remove from HashMap
     *
     * @param id - user's id
     */
    @Override
    public void deleteById(Long id) {
        booksById.remove(id);
    }

    /**
     * Finding books by id
     *
     * @param id - book's id
     * @return the book
     */
    @Override
    public Book getOne(Long id) {
        return booksById.get(id);
    }

    /**
     * Finding all books contains in storage
     *
     * @return {@code List} with all books
     */
    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        for (Map.Entry<Long, Book> entry : booksById.entrySet()) {
            books.add(entry.getValue());
        }
        return books;
    }
}
