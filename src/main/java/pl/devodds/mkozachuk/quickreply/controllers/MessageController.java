package pl.devodds.mkozachuk.quickreply.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.devodds.mkozachuk.quickreply.models.Category;
import pl.devodds.mkozachuk.quickreply.models.Message;
import pl.devodds.mkozachuk.quickreply.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/messages")
public class MessageController {
    private MessageRepository messageRepository;
    private CategoryController categoryController;

    public MessageController(MessageRepository messageRepository, CategoryController categoryController) {
        this.messageRepository = messageRepository;
        this.categoryController = categoryController;
    }

    public List<Message> getAllMsgs(){
        return (List<Message>) messageRepository.findAll();
    }

    public List<Message> getMsgByCategoty(Category category){
        return messageRepository.getMessagesByCategory(category);
    }


    @GetMapping("/all")
    public String allMessages(Model model) {
        List<Message> allList = new ArrayList<>(getAllMsgs());
        model.addAttribute("allList", allList);
        List<String> allmsg = new ArrayList<>();
        for (int i = 0; i < allList.size(); i++) {
            int num = i + 1;
            allmsg.add("#msg" + num );

        }
        model.addAttribute("allMsg", allmsg);

        return "all";
    }

    @GetMapping("/email")
    public String emails(Model model){
        addCategorizedMessages(model,0);
        return "email";
    }

    @GetMapping("/messenger")
    public String messengers(Model model){
        addCategorizedMessages(model, 1);
        return "messenger";
    }

    @GetMapping("/social")
    public String social(Model model){
        addCategorizedMessages(model, 2);
        return "social";
    }

    @ModelAttribute(name = "message")
    public Message message() {
        return new Message();
    }

    @GetMapping("/add")
    public String customReply(Model model){
        model.addAttribute("newMessage", message());
        List<Category> allCategories = new ArrayList<>(categoryController.getAll());
        model.addAttribute("allCategories", allCategories);

        return "add";
    }

    @PostMapping("/add")
    public String processCustomReply(@ModelAttribute("message") Message message, Errors errors){
        message.setCategory(categoryController.findById(Long.valueOf(message.getCat_id())));
        System.out.println(message.getCategory());
        System.out.println(errors);

        messageRepository.save(message);

        return "redirect:/messages/all";
    }


    public void addCategorizedMessages(Model model, int categoryId){
        List<Message> alllMsgs = new ArrayList<>(getMsgByCategoty(categoryController.getAll().get(categoryId)));
        model.addAttribute("allMsgs", alllMsgs);
        List<String> divNames = new ArrayList<>();
        for (int i = 0; i < alllMsgs.size(); i++) {
            int num = i + 1;
            divNames.add("#msg" + num + "_div");
        }
        model.addAttribute("divNames", divNames);

    }

}
