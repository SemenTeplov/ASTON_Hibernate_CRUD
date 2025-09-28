package crud.storage;

import crud.dal.Repository;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDbStorage implements UserStorage {
    private final Repository repository;

    @Autowired
    public UserDbStorage(Repository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User update(User user) {
        User tmpUser = read(user.getId());

        if (tmpUser != null) {
            tmpUser.setName(user.getName());
            tmpUser.setEmail(user.getEmail());
            tmpUser.setAge(user.getAge());
            tmpUser.setDate(user.getDate());
        }

        return create(tmpUser);
    }

    @Override
    public User delete(User user) {
        repository.delete(user);

        return user;
    }
}
