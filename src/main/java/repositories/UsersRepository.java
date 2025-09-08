package repositories;

import database.Datebase;

import lombok.extern.slf4j.Slf4j;

import models.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

@Slf4j
public class UsersRepository implements Repository {
    private final Session session;
    private final Transaction transaction;

    public UsersRepository() {
        session = Datebase.getSession();
        transaction = session.beginTransaction();
    }

    @Override
    public void create(User user) {
        Optional<User> opUser = Optional.ofNullable(user);

        if (opUser.isPresent()) {
            session.save(user);
            transaction.commit();

            log.info("User created");
        } else {
            log.warn("Argument is null");
        }
    }

    @Override
    public User read(int id) {
        User user;

        user = session.get(User.class, id);
        transaction.commit();

        log.info("Got user");

        return user;
    }

    @Override
    public void update(User user) {
        Optional<User> opUser = Optional.ofNullable(user);

        if (opUser.isPresent()) {
            session.update(user);
            transaction.commit();

            log.info("User updated");
        } else {
            log.warn("Argument is null");
        }
    }

    @Override
    public void delete(User user) {
        Optional<User> opUser = Optional.ofNullable(user);

        if (opUser.isPresent()) {
            session.delete(user);
            transaction.commit();

            log.info("User deleted");
        } else {
            log.warn("Argument is null");
        }
    }
}
