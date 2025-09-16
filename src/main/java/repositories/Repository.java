package repositories;

import models.User;

public interface Repository {
    boolean create(User user);
    User read(int id);
    boolean update(User user);
    boolean delete(User user);
}
