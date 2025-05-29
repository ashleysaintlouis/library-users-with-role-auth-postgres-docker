package io.github.ashleysaintlouis.userwithrolepostgres.models.book;

import io.github.ashleysaintlouis.userwithrolepostgres.models.author.Author;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Genre genre;

    @Column(nullable = false)
    private LocalDate date_publisher;

    @Column(nullable = false)
    private LocalDate date_register;

    @Column
    private LocalDate last_update;

    @Column
    private String imageUrl;

    @Column(nullable = false, unique = true, length = 13)
    private String isbn;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Author id_author;

    public Author getId_author() {
        return id_author;
    }

    public void setId_author(Author id_author) {
        this.id_author = id_author;
    }
//private List <Author> authors;


    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate_publisher() {
        return date_publisher;
    }

    public void setDate_publisher(LocalDate date_publisher) {
        this.date_publisher = date_publisher;
    }

    public LocalDate getDate_register() {
        return date_register;
    }

    public void setDate_register(LocalDate date_register) {
        this.date_register = date_register;
    }

    public LocalDate getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDate last_update) {
        this.last_update = last_update;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
