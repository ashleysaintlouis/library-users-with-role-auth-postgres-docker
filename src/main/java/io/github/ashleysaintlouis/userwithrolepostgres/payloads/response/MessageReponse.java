package io.github.ashleysaintlouis.userwithrolepostgres.payloads.response;

import org.springframework.http.HttpStatus;

public class MessageReponse {
    private String message;
    public MessageReponse(String message, HttpStatus created) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
