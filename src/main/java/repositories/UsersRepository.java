package repositories;

import database.Datebase;

import lombok.extern.slf4j.Slf4j;

import models.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
        session.save(user);
        transaction.commit();

        log.info("User created");
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
        session.update(user);
        transaction.commit();

        log.info("User updated");
    }

    @Override
    public void delete(User user) {
        session.delete(user);
        transaction.commit();

        log.info("User deleted");
    }
}
