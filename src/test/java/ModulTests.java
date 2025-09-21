import Services.APIService;

import models.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ModulTests {
    @Mock
    APIService service;

    @Test
    void serviceCreate() {
        User user = new User(1, "name1", "email@.com", 23, LocalDate.now());

        Mockito.when(service.create()).thenReturn(user);

        assertEquals(user, service.create());
    }

    @Test
    void serviceRead() {
        Mockito.when(service.read()).thenReturn(1);

        assertEquals(1, service.read());
    }

    @Test
    void serviceUpdate() {
        User user = new User(1, "name1", "email@.com", 23, LocalDate.now());

        Mockito.when(service.update()).thenReturn(user);

        assertEquals(user, service.update());

    }

    @Test
    void serviceDelete() {
        User user = new User(1, "name1", "email@.com", 23, LocalDate.now());

        Mockito.when(service.delete()).thenReturn(user);

        assertEquals(user, service.delete());
    }
}
