package Services;

import lombok.AllArgsConstructor;
import models.User;

import repositories.Repository;

@AllArgsConstructor
public class UserService {
    private final Repository repository;

    public boolean create(User user) {
        return repository.create(user);
    }

    public User read(int id) {
        return repository.read(id);
    }

    public boolean update(User user) {
        return repository.update(user);
    }

    public boolean delete(User user) {
        return repository.delete(user);
    }
}
