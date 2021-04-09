package pl.devodds.mkozachuk.quickreply.controllers;

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
import pl.devodds.mkozachuk.quickreply.service.CategoryService;
import pl.devodds.mkozachuk.quickreply.service.MessageService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/messages")
public class MessageController {
    private MessageService messageService;
    private CategoryService categoryService;

    public MessageController(MessageService messageService, CategoryService categoryService) {
        this.messageService = messageService;
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public String allMessages(Model model) {
        List<Message> allList = new ArrayList<>(messageService.getAllMessages());
        model.addAttribute("allList", allList);
        List<String> allmsg = new ArrayList<>();
        for (int i = 0; i < allList.size(); i++) {
            int num = i + 1;
            allmsg.add("#msg" + num);

        }
        model.addAttribute("allMsg", allmsg);

        return "all";
    }

    @GetMapping("/email")
    public String emails(Model model) {
        messageService.addCategorizedMessages(model, 0);
        return "email";
    }

    @GetMapping("/messenger")
    public String messengers(Model model) {
        messageService.addCategorizedMessages(model, 1);
        return "messenger";
    }

    @GetMapping("/social")
    public String social(Model model) {
        messageService.addCategorizedMessages(model, 2);
        return "social";
    }

    @ModelAttribute(name = "message")
    public Message message() {
        return new Message();
    }

    @GetMapping("/add")
    public String customReply(Model model) {
        model.addAttribute("newMessage", message());
        List<Category> allCategories = new ArrayList<>(categoryService.getAll());
        model.addAttribute("allCategories", allCategories);

        return "add";
    }

    @PostMapping("/add")
    public String processCustomReply(@ModelAttribute("message") Message message, Errors errors) {
        message.setCategory(categoryService.findById(Long.valueOf(message.getCat_id())));
        log.info("{}", message.getCategory());
        log.warn("{}", errors);

        messageService.save(message);

        return "redirect:/messages/all";
    }


}
