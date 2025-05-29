package io.github.ashleysaintlouis.userwithrolepostgres.controllers.author;

import io.github.ashleysaintlouis.userwithrolepostgres.payloads.request.authors.AuthorDto;
import io.github.ashleysaintlouis.userwithrolepostgres.security.services.authors.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;


    @PostMapping
    public ResponseEntity<?> addAuthor(@RequestBody AuthorDto author) {
        return authorService.saveAuthor(author);
    }
}
