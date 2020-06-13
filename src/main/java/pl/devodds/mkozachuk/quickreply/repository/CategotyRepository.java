package pl.devodds.mkozachuk.quickreply.repository;

import org.springframework.data.repository.CrudRepository;
import pl.devodds.mkozachuk.quickreply.models.Category;

public interface CategotyRepository extends CrudRepository<Category, Long> {
}
