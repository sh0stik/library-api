package com.gd.libraryapi.repository.map;

import com.gd.libraryapi.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ComponentScan("com.gd.libraryapi.repository.map")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryMapImplTest {

    private BookRepositoryMapImpl bookRepository = new BookRepositoryMapImpl();

    private Book bookHarryPotter = new Book("Harry Potter", "J. K. Rowling", "Fantasy");
    private Book bookGoneGirl = new Book("Gone girl", "Gillian Flynn", "Novel");
    private Book bookWarAndPeace = new Book("War and Peace", "Leo Tolstoy", "Novel");
    private Book bookGoneWithTheWind = new Book("Gone with the wind", "Margaret Mitchell", "Novel");

    @Test
    public void save() {
        bookHarryPotter.setId(3L);
        assertEquals(bookHarryPotter.toString(), bookRepository.save(bookHarryPotter).toString());
    }

    @Before
    public void savedForTest() {
        bookRepository.save(bookGoneGirl);
        bookRepository.save(bookWarAndPeace);
        bookRepository.save(bookGoneWithTheWind);
    }

    @Test
    public void deleteById() {
        bookRepository.deleteById(0L);
        assertEquals(2, bookRepository.findAll().size());
    }

    @Test
    public void getOne() {
        assertEquals(bookGoneGirl.toString(), bookRepository.getOne(0L).toString());
    }

    @Test
    public void findAll() {
        assertEquals(3, bookRepository.findAll().size());
    }
}