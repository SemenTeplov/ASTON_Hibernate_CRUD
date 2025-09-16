package database;

import lombok.extern.slf4j.Slf4j;

import models.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

@Slf4j
public class PostgreSQLConnector implements DatabaseConnector {
    private final Configuration configuration = new Configuration().configure().addAnnotatedClass(User.class);
    private final ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
    private final SessionFactory sf = configuration.buildSessionFactory(sr);

    @Override
    public Session getSession() {
        log.info("Session opened");

        return sf.openSession();
    }
}
