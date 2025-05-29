package io.github.ashleysaintlouis.userwithrolepostgres.repositories.bookRepository;

import io.github.ashleysaintlouis.userwithrolepostgres.models.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
