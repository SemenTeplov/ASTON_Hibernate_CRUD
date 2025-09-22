import Services.APIService;
import Services.UserService;

import models.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.Repository;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ModulTests {
    User user = new User(1, "name1", "email@.com", 23, LocalDate.now());

    @Mock
    APIService service;

    @Mock
    Repository repository;

    @InjectMocks
    UserService userService;

    @Test
    void serviceCreate() {
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
        Mockito.when(service.update()).thenReturn(user);

        assertEquals(user, service.update());

    }

    @Test
    void serviceDelete() {
        Mockito.when(service.delete()).thenReturn(user);

        assertEquals(user, service.delete());
    }

    @Test
    void userServiceCreateCheckArgs() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        Mockito.when(userService.create(captor.capture())).thenReturn(true);

        userService.create(user);

        assertEquals(user, captor.getValue());
    }

    @Test
    void userServiceReadCheckArgs() {
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);

        Mockito.when(userService.read(captor.capture())).thenReturn(user);

        userService.read(1);

        assertEquals(Integer.valueOf(1), captor.getValue());
    }

    @Test
    void userServiceUpdateCheckArgs() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        Mockito.when(userService.update(captor.capture())).thenReturn(true);

        userService.update(user);

        assertEquals(user, captor.getValue());
    }

    @Test
    void userServiceDeleteCheckArgs() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        Mockito.when(userService.delete(captor.capture())).thenReturn(true);

        userService.delete(user);

        assertEquals(user, captor.getValue());
    }
}
