package pl.devodds.mkozachuk.quickreply.repository;

import org.springframework.data.repository.CrudRepository;
import pl.devodds.mkozachuk.quickreply.models.Category;
import pl.devodds.mkozachuk.quickreply.models.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> getMessagesByCategory(Category category);
}
