package com.gd.libraryapi.controller;

import com.gd.libraryapi.model.User;
import com.gd.libraryapi.service.BookService;
import com.gd.libraryapi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@ComponentScan("com.gd.libraryapi.controller")
@WebMvcTest(UserController.class)
@WebAppConfiguration
public class UserControllerTest {

    //TODO: create tests for controllers
    @Autowired
    private MockMvc mockMvc;


    private UserService mockUserService = mock(UserService.class);
    private BookService mockBookService = mock(BookService.class);


    private UserController userController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    public UserControllerTest() {
        userController = new UserController(mockUserService, mockBookService);
    }

    private User userIvanov = new User("Ivan", "Ivanov", "ta");
    private User userPetrov = new User("Petr", "Petrov", "pa");

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = Arrays.asList(userIvanov, userPetrov);
        when(mockUserService.getAllUsers()).thenReturn(users);
        mockMvc.perform(get("library-api/v1/users/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is(userIvanov.getFirstName())))
                .andExpect(jsonPath("$[1].firstName", is(userPetrov.getFirstName())));
        verify(mockUserService, times(1)).getAllUsers();
        verifyNoMoreInteractions(mockUserService);

    }

    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform(delete("library-api/v1/users/id=1"))
                .andExpect(status().isOk());
        mockMvc.perform(get("library-api/v1/users/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void findUserById() {
    }

    @Test
    public void addUser1() {
    }

    @Test
    public void getLikedBooks1() {
    }

    @Test
    public void getDislikedBooks() {
    }

    @Test
    public void isLikedBook() {
    }
}