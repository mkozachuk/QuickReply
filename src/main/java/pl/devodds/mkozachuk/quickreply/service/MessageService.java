package pl.devodds.mkozachuk.quickreply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.devodds.mkozachuk.quickreply.models.Category;
import pl.devodds.mkozachuk.quickreply.models.Message;
import pl.devodds.mkozachuk.quickreply.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private CategoryService categoryService;

    @Autowired
    public MessageService(MessageRepository messageRepository, CategoryService categoryService){
        this.messageRepository = messageRepository;
        this.categoryService = categoryService;
    }

    public Message save(Message message){
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    public List<Message> getMsgByCategory(Category category) {
        return messageRepository.getMessagesByCategory(category);
    }

    public void addCategorizedMessages(Model model, int categoryId) {
        List<Message> alllMsgs = new ArrayList<>(getMsgByCategory(categoryService.getAll().get(categoryId)));
        model.addAttribute("allMsgs", alllMsgs);
        List<String> divNames = new ArrayList<>();
        for (int i = 0; i < alllMsgs.size(); i++) {
            int num = i + 1;
            divNames.add("#msg" + num);
        }
        model.addAttribute("divNames", divNames);

    }
}
