package repositories;

import database.Datebase;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import models.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

@Slf4j
public class UsersRepository implements Repository {
    private Session session;
    private Transaction transaction;

    @Override
    public void create(@NonNull User user) {
        open();
        session.save(user);
        close();

        log.info("User created");
    }

    @Override
    public User read(int id) {
        User user;

        open();
        user = session.get(User.class, id);
        close();

        log.info("Got user");

        return user;
    }

    @Override
    public void update(@NonNull User user) {
        open();
        session.update(user);
        close();

        log.info("User updated");
    }

    @Override
    public void delete(@NonNull User user) {
        open();
        session.delete(user);
        close();

        log.info("User deleted");
    }

    private void open() {
        session = Datebase.getSession();
        transaction = session.beginTransaction();
    }

    private void close() {
        transaction.commit();
        session.close();
    }
}
