package crud.storage;

import crud.dal.UserRepository;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDbStorage implements UserStorage {
    private final UserRepository repository;

    @Autowired
    public UserDbStorage(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        return repository.create(user);
    }

    @Override
    public User read(Integer id) {
        return repository.read(id);
    }

    @Override
    public User update(User user) {
        return repository.update(user);
    }

    @Override
    public User delete(User user) {
        return repository.delete(user);
    }
}
