package com.gd.libraryapi.service.impl;

import com.gd.libraryapi.model.Book;
import com.gd.libraryapi.model.User;
import com.gd.libraryapi.repository.UserRepository;
import com.gd.libraryapi.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.gd.libraryapi.service.impl.db")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserRepository userRepoMock = mock(UserRepository.class);
    private BookService bookServiceMock = mock(BookService.class);

    public UserServiceImplTest() {
        userService = new UserServiceImpl();
        userService.setUserRepository(userRepoMock);
        userService.setBookService(bookServiceMock);
    }

    private User userIvanov = new User("Ivan", "Ivanov", "ta");
    private User userPetrov = new User("Petr", "Petrov", "pa");


    @Test
    public void testAddUser() {
        userIvanov.setId(1L);
        when(userRepoMock.save(userIvanov)).thenReturn(userIvanov);
        assertEquals(userIvanov.toString(), userService.addUser(userIvanov).toString());
    }


    @Test
    public void testFindUserById() {
        userIvanov.setId(1L);
        when(userRepoMock.getOne(1L)).thenReturn(userIvanov);
        assertEquals(userIvanov.toString(), userService.findUserById(1L).toString());
    }

    @Test
    public void testGetAllUsers() {
        when(userRepoMock.findAll()).thenReturn(Arrays.asList(userIvanov, userPetrov));
        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    public void testLikedBooks() {
        Book book = new Book("Harry Potter", "J. K. Rowling", "Fantasy");
        userIvanov.getLikedBooks().add(book);
        assertEquals(book.toString(), userService.likedBooks(userIvanov).get(0).toString());
    }

    @Test
    public void testPutLabelToBook() {
        Book bookOne = new Book("ma", "kd", "ldk");
        Book bookTwo = new Book("uel", "kh", "kdjrem");
        when(bookServiceMock.updateBook(bookOne)).thenReturn(bookOne);
        when(bookServiceMock.updateBook(bookTwo)).thenReturn(bookTwo);
        assertTrue(userService.isLikedBook(userIvanov, bookOne, "like"));
        assertFalse(userService.isLikedBook(userPetrov, bookTwo, "dislike"));
    }

}