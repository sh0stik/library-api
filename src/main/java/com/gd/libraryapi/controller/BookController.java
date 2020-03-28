package com.gd.libraryapi.controller;

import com.gd.libraryapi.model.Book;
import com.gd.libraryapi.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BookController class is RestController
 */
@RestController
public class BookController {
    private Logger logger = LogManager.getLogger(BookController.class);
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * GET method to find all books contains in storage
     *
     * @return list of books
     */
    @GetMapping(value = "library-api/v1/books/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    /**
     * DELETE method to delete book by id
     *
     * @param id id of book which we want delete
     */
    @DeleteMapping(value = "library-api/v1/books/id={book_id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteBook(@PathVariable("book_id") Long id) {
        logger.info("(Service Side) Deleting book with id: " + id);
        bookService.deleteBook(id);
    }

    /**
     * GET method to find book by id
     *
     * @param id - id of book which we want find
     * @return book
     */
    @GetMapping(value = "library-api/v1/books/id={book_id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Book findBookById(@PathVariable("book_id") Long id) {
        return bookService.findBookById(id);
    }

    /**
     * GET method to find book by name
     *
     * @param name - title of book which we want find
     * @return book
     * @see BookService
     */
    @GetMapping(value = "library-api/v1/books/name={book_name}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Book findBookByName(@PathVariable("book_name") String name) {
        return bookService.findBookByName(name);
    }

    /**
     * GET method to find books by author
     *
     * @param author - author whose books we want find
     * @return books by author
     * @see BookService
     */
    @GetMapping(value = "library-api/v1/books/author={author}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Book> findBookByAuthor(@PathVariable("author") String author) {
        return bookService.findBookByAuthor(author);
    }

    /**
     * POST method to save new book in storage
     *
     * @param book - book which we want save
     * @return saved book
     */
    @PostMapping(value = "library-api/v1/books/add/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    /**
     * PUT method to update book
     *
     * @param id   - book's id which we want update
     * @param book - body of book with changes
     * @return updated book
     */
    @PutMapping(value = "library-api/v1/books/id={book_id}/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Book updateBook(@PathVariable("book_id") Long id, @RequestBody Book book) {
        book.setId(id);
        return bookService.updateBook(book);
    }

    /**
     * GET method to get top ten books
     *
     * @return top ten books
     * @see BookService
     */
    @GetMapping(value = "library-api/v1/books/top/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Book> getTopTenFavoriteBooks() {
        return bookService.getTopTenFavoriteBooks(bookService.findAllBooks());
    }
}
