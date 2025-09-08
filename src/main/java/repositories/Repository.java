package repositories;

import models.User;

public interface Repository {
    void create(User user);
    User read(int id);
    void update(User user);
    void delete(User user);
}
