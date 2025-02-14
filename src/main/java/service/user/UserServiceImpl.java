package service.user;

import entity.Note;
import entity.User;
import lombok.AllArgsConstructor;
import repository.user.UserRepository;

import java.util.List;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public boolean add(User user) {
        return userRepository.add(user);
    }

    @Override
    public User add(String gmail, String username, String password, String salt) {
        User user = User.builder()
                .gmail(gmail)
                .username(username)
                .password(password)
                .salt(salt)
                .build();
        add(user);
        return user;
    }

    @Override
    public boolean hasGmail(String gmail) {
        return userRepository.getByGmail(gmail).isPresent();
    }

    @Override
    public boolean hasId(Long id) {
        return userRepository.getById(id).isPresent();
    }

    @Override
    public User getByGmail(String gmail) {
        return userRepository.getByGmail(gmail).isPresent() ? userRepository.getByGmail(gmail).get() : null;
    }
}
