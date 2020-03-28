package com.gd.libraryapi.repository.db;

import com.gd.libraryapi.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ComponentScan("com.gd.libraryapi.repository.db")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DataJpaTest
public class BookRepositoryDBImplTest {

    @Autowired
    private BookRepositoryDBImpl bookRepository;

    private Book bookHarryPotter = new Book("Harry Potter", "J. K. Rowling", "Fantasy");
    private Book bookGoneGirl = new Book("Gone girl", "Gillian Flynn", "Novel");
    private Book bookWarAndPeace = new Book("War and Peace", "Leo Tolstoy", "Novel");
    private Book bookGoneWithTheWind = new Book("Gone with the wind", "Margaret Mitchell", "Novel");

    @Test
    public void testAddBook() {
        bookHarryPotter.setId(1L);
        assertEquals(bookHarryPotter.toString(), bookRepository.save(bookHarryPotter).toString());
    }

    @Test
    public void testGetBook() {
        bookGoneGirl.setId(1L);
        bookRepository.save(bookGoneGirl);
        assertEquals(bookGoneGirl.toString(), bookRepository.getOne(1L).toString());
    }

    @Before
    public void saveEntityForTest() {
        bookGoneGirl.setId(1L);
        bookWarAndPeace.setId(2L);
        bookGoneWithTheWind.setId(3L);
        bookRepository.save(bookGoneGirl);
        bookRepository.save(bookWarAndPeace);
        bookRepository.save(bookGoneWithTheWind);
    }

    @Test
    public void testGetAllBooks() {
        saveEntityForTest();
        List<Book> books = Arrays.asList(bookGoneGirl, bookWarAndPeace, bookGoneWithTheWind);
        assertEquals(books.size(), bookRepository.findAll().size());
        assertEquals(books.get(0).toString(), bookRepository.findAll().get(0).toString());
        assertEquals(books.get(1).toString(), bookRepository.findAll().get(1).toString());
        assertEquals(books.get(2).toString(), bookRepository.findAll().get(2).toString());
    }

    @Test
    public void positiveTestDeleteBook() {
        bookRepository.deleteById(1L);
        assertEquals(2, bookRepository.findAll().size());
    }

    @Test(expected = JpaObjectRetrievalFailureException.class)
    public void negativeTestDeleteBook() {
        bookRepository.deleteById(1L);
        bookRepository.getOne(1L);
    }

    @Test
    public void testUpdateBook() {
        Book book = bookRepository.getOne(2L);
        book.setName("Harry Potter");
        assertEquals(book.toString(), bookRepository.save(book).toString());
    }
}