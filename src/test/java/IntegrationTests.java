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

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegrationTests {
    static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>("postgres");

    Repository repository;

    @BeforeAll
    static void beforeAll() {
        postgresql.start();
    }

    @AfterAll
    static void afterAll() {
        postgresql.stop();
    }

    @BeforeEach
    void setUp() throws SQLException {
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
    }

    @Test
    void create() {
        User user = new User(1, "name1", "email@.com", 23, LocalDate.now());

        repository.create(user);
        assertEquals(user.toString(), repository.read(1).toString());
    }

    @Test
    void wrongCreate() {
        assertThrows(NullPointerException.class, () -> repository.create(null));
    }

    @Test
    void wrongRead() {
        assertNull(repository.read(1));
    }

    @Test
    void update() {
        User user = new User(1, "name1", "email@.com", 23, LocalDate.now());

        repository.create(user);
        user.setName("nameChange");
        repository.update(user);

        assertEquals(user.toString(), repository.read(1).toString());
    }

    @Test
    void wrongUpdate() {
        assertThrows(NullPointerException.class, () -> repository.update(null));
    }

    @Test
    void delete() {
        User user = new User(1, "name1", "email@.com", 23, LocalDate.now());

        repository.create(user);
        repository.delete(user);

        assertNull(repository.read(1));
    }

    @Test
    void wrongDelete() {
        assertThrows(NullPointerException.class, () -> repository.delete(null));
    }
}