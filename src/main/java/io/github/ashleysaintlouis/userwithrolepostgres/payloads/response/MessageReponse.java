package io.github.ashleysaintlouis.userwithrolepostgres.payloads.response;

public class MessageReponse {
    private String message;
    public MessageReponse(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
