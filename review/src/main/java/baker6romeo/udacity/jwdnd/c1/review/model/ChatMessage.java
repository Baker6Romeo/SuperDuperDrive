package baker6romeo.udacity.jwdnd.c1.review.model;

import org.springframework.stereotype.Component;

@Component
public class ChatMessage {

    public String getMessage() {
        return message;
    }

    public void setMessage(String messageText) {
        this.message = messageText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String message;
    private String username;

}