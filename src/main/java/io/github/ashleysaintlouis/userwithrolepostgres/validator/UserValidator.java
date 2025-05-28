package io.github.ashleysaintlouis.userwithrolepostgres.validator;

import io.github.ashleysaintlouis.userwithrolepostgres.exceptions.user.RegistroDuplicadoException;
import io.github.ashleysaintlouis.userwithrolepostgres.payloads.request.SignupRequest;
import io.github.ashleysaintlouis.userwithrolepostgres.repositories.usersRepositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    private final UserRepository userRepository;
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validarUser(SignupRequest signupRequest) {

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new RegistroDuplicadoException("Username já existe!");
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RegistroDuplicadoException("Email já existe!");
        }
    }
}
