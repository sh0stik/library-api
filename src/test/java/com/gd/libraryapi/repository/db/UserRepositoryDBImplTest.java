package com.gd.libraryapi.repository.db;

import com.gd.libraryapi.model.User;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ComponentScan("com.gd.libraryapi.repository.db")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DataJpaTest
public class UserRepositoryDBImplTest {
    @Autowired
    private UserRepositoryDBImpl userRepository;

    private User userAdmin = new User("admin", "admin", "admin");
    private User userShostak = new User("Daria", "Shostak", "shostik");
    private User userIvanova = new User("Masha", "Ivanova", "manechka");
    private User userIvanov = new User("Ivan", "Ivanov", "ivanushka");
    private User userPetrov = new User("Petr", "Petrov", "petya");

    @Test
    public void testAddUser() {
        userAdmin.setId(1L);
        assertEquals(userAdmin.toString(), userRepository.save(userAdmin).toString());
    }

    @Before
    public void saveEntityForTest() {
        userShostak.setId(1L);
        userIvanova.setId(2L);
        userIvanov.setId(3L);
        userPetrov.setId(4L);
        userRepository.save(userShostak);
        userRepository.save(userIvanova);
        userRepository.save(userIvanov);
        userRepository.save(userPetrov);
    }

    @Test
    public void testGetUser() {
        userShostak.setId(1L);
        assertEquals(userShostak.toString(), userRepository.getOne(1L).toString());
    }

    @Test
    public void testGetAllUsers() {
        userShostak.setId(1L);
        userIvanova.setId(2L);
        userIvanov.setId(3L);
        userPetrov.setId(4L);
        List<User> books = new ArrayList<>();
        books.add(userShostak);
        books.add(userIvanova);
        books.add(userIvanov);
        books.add(userPetrov);
        assertEquals(books.size(), userRepository.findAll().size());
        assertEquals(books.toString(), userRepository.findAll().toString());
    }

    @Test
    public void positiveTestDeleteUser() {
        userRepository.deleteById(1L);
        assertEquals(3, userRepository.findAll().size());
    }

    @Test(expected = JpaObjectRetrievalFailureException.class)
    public void negativeTestDeleteUser() {
        userRepository.deleteById(1L);
        userRepository.getOne(1L);
    }

    @Test
    public void testUpdateUser() {
        User user = userRepository.getOne(2L);
        user.setFirstName("Harry");
        assertEquals(user.toString(), userRepository.save(user).toString());
    }


}