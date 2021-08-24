package baker6romeo.udacity.jwdnd.c1.review.service;

import baker6romeo.udacity.jwdnd.c1.review.mapper.ChatMessageMapper;
import baker6romeo.udacity.jwdnd.c1.review.model.ChatForm;
import baker6romeo.udacity.jwdnd.c1.review.model.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private List<ChatMessage> chatMessages; // Initialized in @PostConstruct method
    private ChatMessageMapper chatMessageMapper;

    public MessageService(ChatMessageMapper chatMessageMapper) {
        this.chatMessageMapper = chatMessageMapper;
    }

    public void addMessage(ChatForm chatForm) {
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUsername(chatForm.getUsername());
        System.out.println(newMessage.getUsername());
        switch (chatForm.getMessageType()) {
            case "Say":
                newMessage.setMessage(chatForm.getMessageText());
                System.out.println(newMessage.getMessage());
                break;
            case "Shout":
                newMessage.setMessage(chatForm.getMessageText().toUpperCase());
                System.out.println(newMessage.getMessage());
                break;
            case "Whisper":
                newMessage.setMessage(chatForm.getMessageText().toLowerCase());
                System.out.println(newMessage.getMessage());
                break;
        }
        int result = chatMessageMapper.insert(newMessage);
        System.out.println("Insert Result is: " + result);
    }

    public List<ChatMessage> getChatMessages() {
        System.out.println("Getting Messages!");
        for (ChatMessage message : chatMessageMapper.getMessages()) {
            System.out.println(message.getMessage());
        }
        return  chatMessageMapper.getMessages();
    }
}