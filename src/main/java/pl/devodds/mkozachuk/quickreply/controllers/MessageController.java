package pl.devodds.mkozachuk.quickreply.controllers;

import org.springframework.stereotype.Controller;
import pl.devodds.mkozachuk.quickreply.repository.MessageRepository;

@Controller
public class MessageController {
    private MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

}
