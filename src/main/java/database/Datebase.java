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

    private static Configuration configuration = new Configuration().configure().addAnnotatedClass(User.class);
    private static ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
    private static SessionFactory sf = configuration.buildSessionFactory(sr);

    public static Session getSession() {
        log.info("Session opened");

        return sf.openSession();
    }
}
