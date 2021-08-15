package baker6romeo.udacity.jwdnd.c1.review.service;

import baker6romeo.udacity.jwdnd.c1.review.model.ChatForm;
import baker6romeo.udacity.jwdnd.c1.review.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    //private String message;
    private List<ChatMessage> chatMessages; // Initialized in @PostConstruct method

    //
    // no longer needed...apparently
    //
//    MessageService(String message){
//        this.message = message;
//    }
//
//    public void addMessage(ChatForm chatForm){
//        this.chatMessages.add(chatForm);
//    }
//
//    public String lowercase(){
//        return this.message.toLowerCase();
//    }
//
//    public String uppercase(){
//        return this.message.toUpperCase();
//    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating Message Service Bean");
        this.chatMessages = new ArrayList<>();
    }

    public void addMessage(ChatForm chatForm) {
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUsername(chatForm.getUsername());
        switch (chatForm.getMessageType()) {
            case "Say":
                newMessage.setMessage(chatForm.getMessageText());
                break;
            case "Shout":
                newMessage.setMessage(chatForm.getMessageText().toUpperCase());
                break;
            case "Whisper":
                newMessage.setMessage(chatForm.getMessageText().toLowerCase());
                break;
        }
        this.chatMessages.add(newMessage);
    }

    public List<ChatMessage> getChatMessages() {
        return  chatMessages;
    }
}