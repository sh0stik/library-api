package com.gd.libraryapi.service.impl;

import com.gd.libraryapi.model.Book;
import com.gd.libraryapi.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.gd.libraryapi.service.impl.db")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookServiceImplTest {

    private BookServiceImpl bookService;
    private BookRepository bookRepoMock = mock(BookRepository.class);

    public BookServiceImplTest() {
        bookService = new BookServiceImpl();
        bookService.setBookRepository(bookRepoMock);
    }

    private Book bookHarryPotter = new Book("Harry Potter", "J. K. Rowling", "Fantasy");
    private Book bookDorianGray = new Book("The Picture of Dorian Gray", "Oscar Wilde", "Gothic and philosophical novel");
    private Book bookGoneGirl = new Book("Gone girl", "Gillian Flynn", "Novel");
    private Book bookWarAndPeace = new Book("War and Peace", "Leo Tolstoy", "Novel");
    private Book bookGoneWithTheWind = new Book("Gone with the wind", "Margaret Mitchell", "Novel");
    private Book bookOne = new Book("one", "one", "one");
    private Book bookTwo = new Book("two", "two", "two");
    private Book bookTree = new Book("tree", "tree", "tree");
    private Book bookFour = new Book("four", "four", "four");
    private Book bookFive = new Book("five", "five", "five");
    private Book bookSix = new Book("six", "six", "six");

    @Test
    public void testAddBook() {
        bookHarryPotter.setId(1L);
        when(bookRepoMock.save(bookHarryPotter)).thenReturn(bookHarryPotter);
        assertEquals(bookHarryPotter.toString(), bookService.addBook(bookHarryPotter).toString());
    }


    @Test
    public void testFindBookById() {
        bookHarryPotter.setId(1L);
        when(bookRepoMock.getOne(1L)).thenReturn(bookHarryPotter);
        assertEquals(bookHarryPotter.toString(), bookService.findBookById(1L).toString());
    }

    @Test
    public void testFindAllBooks() {
        when(bookRepoMock.findAll()).thenReturn(Arrays.asList(bookDorianGray, bookHarryPotter));
        assertEquals(2, bookService.findAllBooks().size());
    }


    @Test
    public void testFindBookByName() {
        when(bookRepoMock.findAll()).thenReturn(Arrays.asList(bookDorianGray, bookHarryPotter));
        assertEquals(bookDorianGray.getAuthor(), bookService.findBookByName(bookDorianGray.getName()).getAuthor());
        assertEquals(bookHarryPotter.getAuthor(), bookService.findBookByName(bookHarryPotter.getName()).getAuthor());
    }

    @Test
    public void testFindBookByAuthor() {
        when(bookRepoMock.findAll()).thenReturn(Arrays.asList(bookDorianGray, bookHarryPotter));
        assertEquals(bookHarryPotter.getName(), bookService.findBookByAuthor(bookHarryPotter.getAuthor()).get(0).getName());
    }

    @Test
    public void testGetTopTenFavoriteBooks() {
        bookOne.setLikesCount(1);
        bookTwo.setLikesCount(2);
        bookTree.setLikesCount(3);
        bookFour.setLikesCount(4);
        bookFive.setLikesCount(5);
        bookSix.setLikesCount(6);
        bookHarryPotter.setLikesCount(8);
        bookDorianGray.setLikesCount(9);
        bookGoneGirl.setLikesCount(10);
        bookGoneWithTheWind.setLikesCount(11);
        bookWarAndPeace.setLikesCount(12);
        when(bookRepoMock.findAll()).thenReturn(Arrays.asList(bookDorianGray, bookHarryPotter, bookFive, bookFour, bookGoneGirl, bookGoneWithTheWind,
                bookOne, bookSix, bookTree, bookTwo, bookWarAndPeace));
        List<Book> expected = new ArrayList<>();
        expected.add(bookWarAndPeace);
        expected.add(bookGoneWithTheWind);
        expected.add(bookGoneGirl);
        expected.add(bookDorianGray);
        expected.add(bookHarryPotter);
        expected.add(bookSix);
        expected.add(bookFive);
        expected.add(bookFour);
        expected.add(bookTree);
        expected.add(bookTwo);
        assertEquals(expected, bookService.getTopTenFavoriteBooks(bookService.findAllBooks()));
    }
}