package repository.type;
import entity.Type;
import repository.CrudRepository;

import java.util.Optional;


public interface TypeRepository {
    Optional<Type> getById(Long id);
}
