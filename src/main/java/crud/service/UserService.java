package crud.service;

import crud.model.User;
import crud.storage.UserStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserService {
    private final UserStorage storage;

    @Autowired
    public UserService(UserStorage storage) {
        this.storage = storage;
    }

    public User create(User user) {
        log.info("User created");
        return storage.create(user);
    }

    public User read(Integer id) {
        log.info("Got user");
        return storage.read(id);
    }

    public User update(User user) {
        log.info("User updated");
        return storage.update(user);
    }

    public User delete(User user) {
        log.info("User deleted");
        return storage.delete(user);
    }
}
