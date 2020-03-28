package com.gd.libraryapi.repository;

import com.gd.libraryapi.model.User;

import java.util.List;

/**
 * This interface is a data access object class for entity {@link User} with CRUD operations
 */
public interface UserRepository {

    User save(User user);

    void deleteById(Long id);

    User getOne(Long id);

    List<User> findAll();
}
