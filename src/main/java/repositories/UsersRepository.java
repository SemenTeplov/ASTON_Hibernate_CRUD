package repositories;

import database.DatabaseConnector;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import models.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Slf4j
@AllArgsConstructor
public class UsersRepository implements Repository {
    private final DatabaseConnector connector;

    @Override
    public boolean create(@NonNull User user) {
        try (Session session = connector.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();

            log.info("User created");

            return true;
        } catch (HibernateException e) {
            log.error("Session was fall, exception: {}", e.getMessage());

            return false;
        }
    }

    @Override
    public User read(int id) {
        User user;

        try (Session session = connector.getSession()) {
            Transaction transaction = session.beginTransaction();
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
    public boolean update(@NonNull User user) {
        try (Session session = connector.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();

            log.info("User updated");

            return true;
        } catch (HibernateException e) {
            log.error("Session was fall, exception: {}", e.getMessage());

            return false;
        }
    }

    @Override
    public boolean delete(@NonNull User user) {
        try (Session session = connector.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();

            log.info("User deleted");

            return true;
        } catch (HibernateException e) {
            log.error("Session was fall, exception: {}", e.getMessage());

            return false;
        }
    }
}
