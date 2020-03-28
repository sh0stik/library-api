package com.gd.libraryapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class User is entity, which contains required fields: {@link Long} id, {@link String} first name,
 * {@link String} last name and {@link String} nick name. Also instance contains
 * {@link List} with favorite books and {@link List} with books that didn't like.
 *
 * @author Daria Shostak
 */

@Entity
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String nickName;

    @OneToMany
    private List<Book> likedBooks;

    @OneToMany
    private List<Book> dislikedBooks;

    /**
     * Constructor - creating a new object with specific values.
     *
     * @param firstName - name of user,
     * @param lastName  - last name of user,
     * @param nickName  - nick name of user;
     *                  also initializes the {@param likedBooks} and {@param dislikedBooks} with the default value.
     */

    public User(String firstName, String lastName, String nickName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.likedBooks = new ArrayList<>();
        this.dislikedBooks = new ArrayList<>();
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<Book> getLikedBooks() {
        return likedBooks;
    }

    public void setLikedBooks(List<Book> likedBooks) {
        this.likedBooks = likedBooks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public List<Book> getDislikedBooks() {
        return dislikedBooks;
    }

    public void setDislikedBooks(List<Book> dislikedBooks) {
        this.dislikedBooks = dislikedBooks;
    }
}
