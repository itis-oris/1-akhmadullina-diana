package repository.user;
import entity.User;
import repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> getById(Long id);
    Optional<User> getByGmail(String gmail);
}
