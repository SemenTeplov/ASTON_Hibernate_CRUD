import Services.UserService;

import database.DatabaseConnector;

import models.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.testcontainers.containers.PostgreSQLContainer;

import repositories.Repository;
import repositories.UsersRepository;

import java.time.LocalDate;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegrationTests {
    static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>("postgres");
    User user = new User(1, "name1", "email@.com", 23, LocalDate.now());

    static Repository repository;
    static UserService service;

    @BeforeAll
    static void beforeAll() {
        postgresql.start();

        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, postgresql.getJdbcUrl());
        properties.put(Environment.USER, postgresql.getUsername());
        properties.put(Environment.PASS, postgresql.getPassword());
        properties.put(Environment.HBM2DDL_AUTO, "create");

        repository = new UsersRepository(new DatabaseConnector() {
            private final Configuration configuration = new Configuration().setProperties(properties).addAnnotatedClass(User.class);
            private final ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            private final SessionFactory sf = configuration.buildSessionFactory(sr);

            @Override
            public Session getSession() {
                return sf.openSession();
            }
        });

        service = new UserService(repository);
    }

    @BeforeEach
    void beforeEach() {
        service.delete(user);
    }

    @AfterAll
    static void afterAll() {
        postgresql.stop();
    }

    @Test
    void create() {
        service.create(user);

        assertEquals(user.toString(), service.read(1).toString());
    }

    @Test
    void wrongCreate() {
        assertThrows(NullPointerException.class, () -> service.create(null));
    }

    @Test
    void wrongRead() {
        assertNull(service.read(1));
    }

    @Test
    void update() {
        service.create(user);
        user.setName("nameChange");
        service.update(user);

        assertEquals(user.toString(), service.read(1).toString());
    }

    @Test
    void wrongUpdate() {
        assertThrows(NullPointerException.class, () -> service.update(null));
    }

    @Test
    void delete() {
        service.create(user);
        service.delete(user);

        assertNull(service.read(1));
    }

    @Test
    void wrongDelete() {
        assertThrows(NullPointerException.class, () -> service.delete(null));
    }
}