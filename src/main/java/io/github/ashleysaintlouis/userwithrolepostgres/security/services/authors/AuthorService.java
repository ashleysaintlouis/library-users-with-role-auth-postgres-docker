package io.github.ashleysaintlouis.userwithrolepostgres.security.services.authors;

import io.github.ashleysaintlouis.userwithrolepostgres.models.author.Author;
import io.github.ashleysaintlouis.userwithrolepostgres.models.users.ERole;
import io.github.ashleysaintlouis.userwithrolepostgres.models.users.Role;
import io.github.ashleysaintlouis.userwithrolepostgres.models.users.User;
import io.github.ashleysaintlouis.userwithrolepostgres.payloads.request.authors.AuthorDto;
import io.github.ashleysaintlouis.userwithrolepostgres.payloads.response.MessageReponse;
import io.github.ashleysaintlouis.userwithrolepostgres.repositories.authorRepositories.AuthorRepository;
import io.github.ashleysaintlouis.userwithrolepostgres.repositories.bookRepository.BookRepository;
import io.github.ashleysaintlouis.userwithrolepostgres.repositories.usersRepositories.RoleRepository;
import io.github.ashleysaintlouis.userwithrolepostgres.repositories.usersRepositories.UserRepository;
import io.github.ashleysaintlouis.userwithrolepostgres.security.services.users.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class AuthorService {
    AuthorRepository authorRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;
    BookRepository bookRepository;


    public AuthorService (AuthorRepository authorRepository,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          BookRepository bookRepository,
                          UserRepository UserRepository) {
        this.authorRepository = authorRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bookRepository = bookRepository;
    }

    public ResponseEntity <?> saveAuthor(AuthorDto authorDto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (authorRepository.findByUser(user).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(new MessageReponse("Error: Já tem um autor vinculado.", HttpStatus.BAD_REQUEST));
        }

        Author author = new Author();
        author.setUser(user);
        author.setUsername(authorDto.username());
        author.setEmail(authorDto.email());
        author.setName(authorDto.name());
        author.setBio(authorDto.bio());
        author.setDateOfBirth(authorDto.dateOfBirth());
        author.setNationality(authorDto.nationality());
        author.setImageUrl(authorDto.imageUrl());
        authorRepository.save(author);

        Role authorRole = roleRepository.findByName(ERole.ROLE_AUTHOR)
                .orElseThrow(() -> new RuntimeException("Error: Role Author não encontrado!"));

        user.getRoles().add(authorRole);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageReponse("Author salvo com sucesso.", HttpStatus.CREATED));
    }

}
