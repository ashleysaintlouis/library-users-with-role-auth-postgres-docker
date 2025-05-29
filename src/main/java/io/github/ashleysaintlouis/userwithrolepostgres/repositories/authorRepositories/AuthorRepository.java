package io.github.ashleysaintlouis.userwithrolepostgres.repositories.authorRepositories;

import io.github.ashleysaintlouis.userwithrolepostgres.models.author.Author;
import io.github.ashleysaintlouis.userwithrolepostgres.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByUser(User user);
}
