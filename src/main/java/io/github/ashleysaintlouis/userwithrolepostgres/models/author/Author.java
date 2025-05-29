package io.github.ashleysaintlouis.userwithrolepostgres.models.author;

import io.github.ashleysaintlouis.userwithrolepostgres.models.book.Book;
import io.github.ashleysaintlouis.userwithrolepostgres.models.users.User;
import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Table
@Entity
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 25, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 350)
    private String bio;

    @Column
    private String imageUrl;

    @Column(nullable = false, length = 20)
    private String nationality;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(unique = true, name = "user_id")
    private User user;

    @OneToMany(mappedBy = "id_author")
    private List<Book> books;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Author() {
    }

    public Author(long id,
                  String email,
                  String username,
                  String name,
                  String bio,
                  String imageUrl,
                  String nationality,
                  LocalDate dateOfBirth,
                  User user) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.imageUrl = imageUrl;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }



}
