package repositories;

import database.Datebase;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import models.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Slf4j
public class UsersRepository implements Repository {
    private Transaction transaction;

    @Override
    public void create(@NonNull User user) {
        try (Session session = Datebase.getSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();

            log.info("User created");
        } catch (HibernateException e) {
            log.error("Session was fall, exception: {}", e.getMessage());
        }
    }

    @Override
    public User read(int id) {
        User user;

        try (Session session = Datebase.getSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();

            log.info("Got user");

            return user;
        } catch (HibernateException e) {
            log.error("Session was fall, exception: {}", e.getMessage());
        }

        return null;
    }

    @Override
    public void update(@NonNull User user) {
        try (Session session = Datebase.getSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();

            log.info("User updated");
        } catch (HibernateException e) {
            log.error("Session was fall, exception: {}", e.getMessage());
        }
    }

    @Override
    public void delete(@NonNull User user) {
        try (Session session = Datebase.getSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();

            log.info("User deleted");
        } catch (HibernateException e) {
            log.error("Session was fall, exception: {}", e.getMessage());
        }
    }
}
