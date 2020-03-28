package com.gd.libraryapi.repository.db;

import com.gd.libraryapi.model.User;
import com.gd.libraryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * There is implementation for {@link UserRepository} using in memory data base H2
 * {@link RepositoryDefinition} means that we use all CRUD methods by {@link org.springframework.data.repository.Repository}
 * {@link Qualifier} we use to eliminate ambiguity in the choice of implementations.
 */
@Qualifier("UserRepositoryDBImpl")
@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepositoryDBImpl extends UserRepository {
}
