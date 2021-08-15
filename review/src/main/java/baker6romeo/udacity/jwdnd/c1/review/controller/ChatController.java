package baker6romeo.udacity.jwdnd.c1.review.controller;

import baker6romeo.udacity.jwdnd.c1.review.model.ChatForm;
import baker6romeo.udacity.jwdnd.c1.review.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/chat")    //Assigns base URL that this controller is responsible for. So no longer have to add
                            // ("/chat") to each mapping function. ie: @GetMapping(/"chat")
public class ChatController {

   private final MessageService messageService;

   public ChatController(MessageService messageService){
       this.messageService = messageService;
   }

   @GetMapping("/chat")
   public String getChatPage(ChatForm chatForm, Model model) {
       model.addAttribute("chatMessages", this.messageService.getChatMessages());
       return "chat";
   }


   @PostMapping("/chat")
   //public void  addMessage(@ModelAttribute("chatForm") ChatForm chatForm){
   public String postChatMessage(ChatForm chatForm, Model model) {
       //if(!isCleanMessage(chatForm.getMessageText())) {
           this.messageService.addMessage(chatForm);
       //}
       chatForm.setMessageText("");    // clear the message form
       model.addAttribute("chatMessages", this.messageService.getChatMessages());  // update the message display
       return "chat";
   }

   @ModelAttribute("allMessageTypes")
   public String[] allMessageTypes () {return new String [] {"Say", "Shout", "Whisper"}; }

   public List<String> getBannedWords(){   // BONUS
       return Arrays.asList("Shit","sh1t","noob","n00b");
   }

   public boolean isCleanMessage(String message) {  // BONUS
       return getBannedWords().stream().anyMatch(message::contains);
   }
}
