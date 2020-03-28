package com.gd.libraryapi.repository.map;

import com.gd.libraryapi.model.User;
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
public class UserRepositoryMapImplTest {
    private UserRepositoryMapImpl userRepository;

    public UserRepositoryMapImplTest() {
        userRepository = new UserRepositoryMapImpl();
    }

    private User userAdmin = new User("admin", "admin", "admin");
    private User userShostak = new User("Daria", "Shostak", "shostik");
    private User userIvanova = new User("Masha", "Ivanova", "manechka");
    private User userIvanov = new User("Ivan", "Ivanov", "ivanushka");
    private User userPetrov = new User("Petr", "Petrov", "petya");

    @Test
    public void save() {
        userAdmin.setId(1L);
        assertEquals(userAdmin, userRepository.save(userAdmin));
    }

    @Before
    public void saveForTests() {
        userRepository.save(userIvanov);
        userRepository.save(userShostak);
        userRepository.save(userPetrov);
        userRepository.save(userIvanova);
    }

    @Test
    public void deleteById() {
        userRepository.deleteById(1L);
        assertEquals(3, userRepository.findAll().size());
    }

    @Test
    public void findAll() {
        assertEquals(4, userRepository.findAll().size());
        assertEquals(userShostak.toString(), userRepository.findAll().get(1).toString());
    }

    @Test
    public void getOne() {
        assertEquals(userPetrov.toString(), userRepository.getOne(2L).toString());
    }
}