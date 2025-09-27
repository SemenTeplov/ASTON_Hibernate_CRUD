package crud.storage;

import crud.model.User;

public interface UserStorage {
    public User create(User user);

    public User read(Integer id);

    public User update(User user);

    public User delete(User user);
}
