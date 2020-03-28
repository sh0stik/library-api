package com.gd.libraryapi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Book is entity we'll use for for description any books.
 * Instance of Book class contains required fields: {@link Long} id, {@link String} name,
 * {@link String} author and {@link String} description of book. Also instance contains
 * {@link Integer} count of likes (it means how many users liked this book) and {@link List} of {@link User}
 * hwo like this book.
 *
 * @author Daria Shostak
 */
@Entity
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String author;

    private String description;

    private Integer likesCount;

    @OneToMany
    private List<User> likes;

    /**
     * Constructor - creating a new object with specific values.
     *
     * @param name        - title of book,
     * @param author      - name of author,
     * @param description - description of book;
     *                    also initializes the {@param likesCount} and {@param likes} with the default value.
     */

    public Book(String name, String author, String description) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.likesCount = 0;
        this.likes = new ArrayList<>();
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", likesCount=" + likesCount +
                '}';
    }
}
