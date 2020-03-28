package com.gd.libraryapi.controller;

import com.gd.libraryapi.model.Book;
import com.gd.libraryapi.model.User;
import com.gd.libraryapi.service.BookService;
import com.gd.libraryapi.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController is Rest Controller
 */
@RestController
public class UserController {
    private Logger logger = LogManager.getLogger(UserController.class);

    private UserService userService;
    private BookService bookService;

    @Autowired
    public UserController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    /**
     * GET method to find all users in storage
     *
     * @return all users
     */
    @GetMapping(value = "library-api/v1/users/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * DELETE method to delete user by id
     *
     * @param id id of user which we want delete
     */
    @DeleteMapping(value = "library-api/v1/users/id={user_id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteUser(@PathVariable("user_id") Long id) {
        logger.info("(Service Side) Deleting user with id: " + id);
        userService.deleteUser(id);
    }

    /**
     * GET method to find user by id
     *
     * @param id - id of user which we want find
     * @return user
     */
    @GetMapping(value = "library-api/v1/users/id={user_id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User findUserById(@PathVariable("user_id") Long id) {
        return userService.findUserById(id);
    }

    /**
     * POST method to save new user in storage
     *
     * @param user - user which we want save
     * @return saved user
     */
    @PostMapping(value = "library-api/v1/users/add/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * GET method to get user's favourite books
     *
     * @return user's favourite books
     * @see UserService
     */
    @GetMapping(value = "library-api/v1/users/id={user_id}/liked_books",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Book> getLikedBooks(@PathVariable("user_id") Long id) {
        return userService.likedBooks(userService.findUserById(id));
    }

    /**
     * GET method to get user's disliked books
     *
     * @return user's disliked books
     * @see UserService
     */
    @GetMapping(value = "library-api/v1/users/id={user_id}/disliked_books",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Book> getDislikedBooks(@PathVariable("user_id") Long id) {
        return userService.dislikedBooks(userService.findUserById(id));
    }

    /**
     * PUT method to add to user's liked/disliked books
     *
     * @param userId      - id of user
     * @param bookId      - id of book
     * @param isLikedFlag - "like" or "dislike"
     * @return true if user liked book, false is user disliked book
     */
    @PutMapping(value = "library-api/v1/users/{is_liked_flag}/userId={user_id}&bookId={book_id}/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean isLikedBook(@PathVariable("user_id") Long userId, @PathVariable("book_id") Long bookId,
                               @PathVariable("is_liked_flag") String isLikedFlag) {
        return userService.isLikedBook(userService.findUserById(userId), bookService.findBookById(bookId), isLikedFlag);
    }
}
