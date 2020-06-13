package pl.devodds.mkozachuk.quickreply.controllers;

import org.springframework.stereotype.Controller;
import pl.devodds.mkozachuk.quickreply.models.Category;
import pl.devodds.mkozachuk.quickreply.models.Message;
import pl.devodds.mkozachuk.quickreply.repository.CategotyRepository;

import java.util.List;

@Controller
public class CategoryController {
    private CategotyRepository categotyRepository;

    public CategoryController(CategotyRepository categotyRepository){
        this.categotyRepository = categotyRepository;
    }

    public List<Category> getAll(){
        return (List<Category>) categotyRepository.findAll();
    }

}
