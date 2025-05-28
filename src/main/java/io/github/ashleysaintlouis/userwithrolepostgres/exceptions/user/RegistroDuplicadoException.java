package io.github.ashleysaintlouis.userwithrolepostgres.exceptions.user;

public class RegistroDuplicadoException extends RuntimeException{
    public RegistroDuplicadoException(String message) {
        super(message);
    }
}
