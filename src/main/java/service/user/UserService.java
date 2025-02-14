package service.user;

import entity.Note;
import entity.User;

import java.util.List;

public interface UserService {
    boolean add(User user);
    User add(String gmail, String username, String password, String salt);
    boolean hasGmail(String gmail);
    boolean hasId(Long id_user);
    User getByGmail(String gmail);
}
