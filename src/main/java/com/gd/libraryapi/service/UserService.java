package com.gd.libraryapi.service;

import com.gd.libraryapi.model.Book;
import com.gd.libraryapi.model.User;

import java.util.List;

/**
 * This interface is a service for User entity.
 */
public interface UserService {
    /**
     * The method saves user to storage.
     *
     * @param user - new instance of {@link User}passed to method.
     * @return {@param user} after saving.
     */
    User addUser(User user);

    /**
     * The method removes user from storage.
     *
     * @param id - {@code Long} id of user passed to method.
     */
    void deleteUser(Long id);

    /**
     * The method finds user by id in storage.
     *
     * @param id - {@code Long} id of user passed to method.
     * @return {@param user}.
     */
    User findUserById(Long id);

    /**
     * The method finds all users contains in storage.
     *
     * @return {@code List} with users.
     */
    List<User> getAllUsers();

    /**
     * The method updates user.
     *
     * @param user - instance of {@link User}passed to method.
     * @return {@param user} after updating.
     */
    User updateUser(User user);

    /**
     * The method returns list with user's favourites books
     *
     * @param user - instance of {@link User}passed to method.
     * @return {@code List} {@link Book}.
     */
    List<Book> likedBooks(User user);

    /**
     * The method returns list with user's disliked books
     *
     * @param user - instance of {@link User}passed to method.
     * @return {@code List} {@link Book}.
     */
    List<Book> dislikedBooks(User user);

    /**
     * The method says whether the user liked the book
     *
     * @param user - instance of {@link User}passed to method.
     * @param book - instance of {@link Book}passed to method.
     * @param flag - "like"/"dislike".
     * @return true - if flag is "like", and false - if flag is "dislike"
     */
    boolean isLikedBook(User user, Book book, String flag);
}
