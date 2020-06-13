package pl.devodds.mkozachuk.quickreply.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.devodds.mkozachuk.quickreply.models.Message;
import pl.devodds.mkozachuk.quickreply.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/messages")
public class MessageController {
    private MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    @GetMapping("/all")
    public String allMessages(Model model) {
        List<Message> allList = new ArrayList<>();
        Message msg1 = new Message("Message 1");
        Message msg2 = new Message("Message 2");
        Message msg3 = new Message("Message 3");
        allList.add(msg1);
        allList.add(msg2);
        allList.add(msg3);
        model.addAttribute("allList", allList);
        List<String> allmsg = new ArrayList<>();
        for (int i = 0; i < allList.size(); i++) {
            int num = i + 1;
            allmsg.add("#msg" + num + "_div");
        }
        model.addAttribute("allMsg", allmsg);

        return "all";
    }

}
