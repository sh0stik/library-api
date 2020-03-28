package com.gd.libraryapi.service;

import com.gd.libraryapi.model.Book;

import java.util.List;

/**
 * This interface is a service for Book entity.
 */
public interface BookService {
    /**
     * The method saves book to storage.
     *
     * @param book - new instance of {@link Book}passed to method.
     * @return {@param book} after saving.
     */
    Book addBook(Book book);

    /**
     * The method updates book.
     *
     * @param book - instance of {@link Book}passed to method.
     * @return {@param book} after updating.
     */
    Book updateBook(Book book);

    /**
     * The method removes book from storage.
     *
     * @param id - {@code Long} id of book passed to method.
     */
    void deleteBook(Long id);

    /**
     * The method finds book by id in storage.
     *
     * @param id - {@code Long} id of book passed to method.
     * @return {@param book}.
     */
    Book findBookById(Long id);

    /**
     * The method finds book by name in storage
     *
     * @param name - {@code String} name of book passed to method.
     * @return {@param book}.
     */
    Book findBookByName(String name);

    /**
     * The method finds list with books by author in storage
     *
     * @param author -{@code String} author of book passed to method
     * @return {@code List} books.
     */
    List<Book> findBookByAuthor(String author);

    /**
     * The method finds all books contains in storage.
     *
     * @return {@code List} with books.
     */
    List<Book> findAllBooks();

    /**
     * The method finds top ten favourite books by all users in storage.
     *
     * @param books - {@code List} books passed to method.
     * @return {@code List} top ten books.
     */
    List<Book> getTopTenFavoriteBooks(List<Book> books);

}
