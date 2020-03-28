package com.gd.libraryapi.controller;

import com.gd.libraryapi.exceptions.LibraryNotFoundException;
import com.gd.libraryapi.exceptions.LibraryServerException;
import com.gd.libraryapi.service.BookService;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ControllerExceptionHandlerTest {

    private MockMvc mockMvc;
    private BookController bookController;
    private BookService bookService = mock(BookService.class);


    public ControllerExceptionHandlerTest() {
        bookController = new BookController(bookService);
        mockMvc = standaloneSetup(bookController)
                .setControllerAdvice(new ControllerExceptionHandler(), new LibraryNotFoundException()).build();
    }

    @Test
    public void handleNotFoundException() throws Exception {
        given(bookService.findBookByAuthor("author")).willThrow(new LibraryNotFoundException());
        mockMvc.perform(get("library-api/v1/books/author=author").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn().getResponse();

    }

    @Test
    public void handleAllExceptions() throws Exception {
        given(bookService.findBookByName("name")).willThrow(new LibraryServerException());
        mockMvc.perform(get("library-api/v1/books/name=name").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn().getResponse();

    }
}