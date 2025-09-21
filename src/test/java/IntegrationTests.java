import models.User;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegrationTests extends Tests {
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