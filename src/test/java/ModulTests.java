import Services.APIService;
import Services.UserService;

import models.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class ModulTests extends Tests {
    @Mock
    APIService service;

    @Test
    void serviceCreate() {
        User user = new User(1, "name1", "email@.com", 23, LocalDate.now());

        Mockito.when(service.getMethod()).thenReturn("1");
        Mockito.when(service.create()).thenReturn(user);

        new Application(service, new UserService(repository)).run();

        Mockito.verify(service).getMethod();
        Mockito.verify(service).create();
    }

    @Test
    void serviceRead() {
        Mockito.when(service.getMethod()).thenReturn("2");
        Mockito.when(service.read()).thenReturn(1);

        new Application(service, new UserService(repository)).run();

        Mockito.verify(service).getMethod();
        Mockito.verify(service).read();
    }

    @Test
    void serviceUpdate() {
        User user = new User(1, "name1", "email@.com", 23, LocalDate.now());

        repository.create(user);
        user.setName("nameChange");

        Mockito.when(service.getMethod()).thenReturn("3");
        Mockito.when(service.update()).thenReturn(user);

        new Application(service, new UserService(repository)).run();

        Mockito.verify(service).getMethod();
        Mockito.verify(service).update();
    }

    @Test
    void serviceDelete() {
        User user = new User(1, "name1", "email@.com", 23, LocalDate.now());

        repository.create(user);

        Mockito.when(service.getMethod()).thenReturn("4");
        Mockito.when(service.delete()).thenReturn(user);

        new Application(service, new UserService(repository)).run();

        Mockito.verify(service).getMethod();
        Mockito.verify(service).delete();
    }
}
