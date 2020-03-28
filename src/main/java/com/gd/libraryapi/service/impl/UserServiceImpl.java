package com.gd.libraryapi.service.impl;

import com.gd.libraryapi.model.Book;
import com.gd.libraryapi.model.User;
import com.gd.libraryapi.repository.UserRepository;
import com.gd.libraryapi.service.BookService;
import com.gd.libraryapi.service.UserService;
import com.gd.libraryapi.service.util.LikedFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is implementation of {@link UserService}
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BookService bookService;

    /**
     * To use another implementation with Map paste to Qualifier ("UserRepositoryMapImpl")
     *
     * @param userRepository - here set implementation  of DAO
     */
    @Autowired
    @Qualifier("UserRepositoryDBImpl")
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<Book> likedBooks(User user) {
        return user.getLikedBooks();
    }

    @Override
    public List<Book> dislikedBooks(User user) {
        return user.getDislikedBooks();
    }

    /**
     * The method compares flag with {@link LikedFlag}:
     * <p>
     * if flag is "like" - book's {@param likesCount} becomes +1, book updated by method updateBook,
     * and book adds to user's list likedBooks, user updated by method save;
     * if flag is "dislike" - book adds to user's list dislikedBooks, user updated by method save;
     * <p/>
     *
     * @param user - instance of {@link User}passed to method.
     * @param book - instance of {@link Book}passed to method.
     * @param flag - "like"/"dislike".
     * @return true if flag is "like" and false if flag is "dislike.
     */
    @Override
    public boolean isLikedBook(User user, Book book, String flag) {
        if (flag.equals(LikedFlag.LIKED.toString())) {
            book.setLikesCount(book.getLikesCount() + 1);
            bookService.updateBook(book);
            user.getLikedBooks().add(book);
            userRepository.save(user);
            return true;
        } else if (flag.equals(LikedFlag.DISLIKED.toString())) {
            user.getDislikedBooks().add(book);
            userRepository.save(user);
        }
        return false;
    }
}
