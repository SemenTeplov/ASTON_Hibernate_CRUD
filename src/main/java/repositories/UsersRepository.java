package repositories;

import database.Datebase;
import exceptions.NullFoundException;

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
        opUser.orElseThrow(NullFoundException::new);

        session.save(opUser.get());
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
        Optional<User> opUser = Optional.ofNullable(user);
        opUser.orElseThrow(NullFoundException::new);

        session.update(opUser.get());
        transaction.commit();

        log.info("User updated");
    }

    @Override
    public void delete(User user) {
        Optional<User> opUser = Optional.ofNullable(user);
        opUser.orElseThrow(NullFoundException::new);

        session.delete(opUser.get());
        transaction.commit();

        log.info("User deleted");
    }
}
