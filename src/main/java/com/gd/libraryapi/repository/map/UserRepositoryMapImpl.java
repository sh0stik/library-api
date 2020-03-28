package com.gd.libraryapi.repository.map;

import com.gd.libraryapi.model.User;
import com.gd.libraryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is implementation for {@link UserRepository} using {@code HashMap}
 * with {@code Long} key and {@link User} value for data storage
 */
@Qualifier("UserRepositoryMapImpl")
@Repository
public class UserRepositoryMapImpl implements UserRepository {
    /**
     * Storage {@param userById}
     */
    private Map<Long, User> usersById = new HashMap<>();
    private long count;

    /**
     * Save entry to storage with auto generating id
     *
     * @param user - new instance of {@link User}passed to method
     * @return the user after putting to HashMap
     */
    public User save(User user) {
        Long id = count++;
        user.setId(id);
        usersById.put(id, user);
        return user;
    }

    /**
     * Deleting by id with using method remove from HashMap
     *
     * @param id - user's id
     */
    public void deleteById(Long id) {
        usersById.remove(id);
    }

    /**
     * Finding all users contains in storage
     *
     * @return {@code List} with all users
     */
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        for (Map.Entry<Long, User> user : usersById.entrySet()) {
            users.add(user.getValue());
        }
        return users;
    }

    /**
     * Finding user by id
     *
     * @param id - user's id
     * @return the user
     */
    @Override
    public User getOne(Long id) {
        return usersById.get(id);
    }

}
