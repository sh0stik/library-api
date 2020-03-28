package com.gd.libraryapi.service.impl;

import com.gd.libraryapi.exceptions.LibraryNotFoundException;
import com.gd.libraryapi.model.Book;
import com.gd.libraryapi.repository.BookRepository;
import com.gd.libraryapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is implementation of {@link BookService}
 */
@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    /**
     * To use another implementation with Map paste to Qualifier ("BookRepositoryMapImpl")
     *
     * @param bookRepository - here set implementation  of DAO
     */
    @Autowired
    @Qualifier("BookRepositoryDBImpl")
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.getOne(id);
    }

    /**
     * The method finds book by name in storage.
     * If book isn't found thrown Runtime Exception.
     *
     * @param name - {@code String} name of book passed to method.
     * @return book.
     */
    @Override
    public Book findBookByName(String name) {
        Book bookFind = null;
        for (Book book : bookRepository.findAll()) {
            if (name.equals(book.getName())) {
                bookFind = book;
            }
        }
        if (Objects.nonNull(bookFind)) {
            return bookFind;
        } else {
            throw new LibraryNotFoundException("Book not found");
        }
    }

    /**
     * The method finds list with books by author in storage
     *
     * @param author -{@code String} author of book passed to method
     * @return list of books found by author
     */
    @Override
    public List<Book> findBookByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            if (author.equals(book.getAuthor())) {
                books.add(book);
            }
        }
        return books;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * The method returns top ten favourite books by all users.
     * List sorted to reduce by liked count and return first ten books.
     *
     * @param books - {@code List} books passed to method.
     * @return list of ten books.
     */
    @Override
    public List<Book> getTopTenFavoriteBooks(List<Book> books) {
        List<Book> topTenBooks = new ArrayList<>(10);
        books.sort((o1, o2) -> o2.getLikesCount().compareTo(o1.getLikesCount()));
        for (int i = 0; i < (books.size() > 10 ? 10 : books.size()); i++) {
            topTenBooks.add(books.get(i));
        }
        return topTenBooks;
    }
}
