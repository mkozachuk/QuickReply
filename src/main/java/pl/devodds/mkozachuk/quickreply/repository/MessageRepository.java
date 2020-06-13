package pl.devodds.mkozachuk.quickreply.repository;

import org.springframework.data.repository.CrudRepository;
import pl.devodds.mkozachuk.quickreply.models.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
