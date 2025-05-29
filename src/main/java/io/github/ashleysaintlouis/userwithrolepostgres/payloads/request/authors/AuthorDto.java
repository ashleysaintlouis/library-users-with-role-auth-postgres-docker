package io.github.ashleysaintlouis.userwithrolepostgres.payloads.request.authors;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record AuthorDto(
        String email,
        String username,
        String name,
        String bio,
        String imageUrl,
        String nationality,
        LocalDate dateOfBirth
) {

}
