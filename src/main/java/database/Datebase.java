package database;

import lombok.extern.slf4j.Slf4j;

import models.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

@Slf4j
public class Datebase {
    private Datebase() {}

    public static Session getSession() {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(User.class);

        try (ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build()) {
            SessionFactory sf = configuration.buildSessionFactory(sr);

            log.info("Session opened");

            return sf.openSession();
        }
    }
}
