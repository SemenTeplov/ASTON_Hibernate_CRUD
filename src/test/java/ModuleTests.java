import crud.model.User;
import crud.service.UserService;
import crud.storage.UserStorage;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ModuleTests {
    User user = new User(1, "name1", "email@.com", 23, LocalDate.now());

    @Mock
    UserStorage storage;

    @InjectMocks
    UserService userService;

    @Test
    void serviceCreate() {
        Mockito.when(userService.create(user)).thenReturn(user);

        Assert.assertEquals(user, userService.create(user));
    }

    @Test
    void serviceRead() {
        Mockito.when(userService.read(1)).thenReturn(user);

        Assert.assertEquals(user, userService.read(1));
    }

    @Test
    void serviceUpdate() {
        Mockito.when(userService.update(user)).thenReturn(user);

        Assert.assertEquals(user, userService.update(user));
    }

    @Test
    void serviceDelete() {
        Mockito.when(userService.delete(user)).thenReturn(user);

        Assert.assertEquals(user, userService.delete(user));
    }

    @Test
    void userServiceCreateCheckArgs() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        Mockito.when(userService.create(captor.capture())).thenReturn(user);

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

        Mockito.when(userService.update(captor.capture())).thenReturn(user);

        userService.update(user);

        assertEquals(user, captor.getValue());
    }

    @Test
    void userServiceDeleteCheckArgs() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        Mockito.when(userService.delete(captor.capture())).thenReturn(user);

        userService.delete(user);

        assertEquals(user, captor.getValue());
    }
}
