import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpStatus;

import crud.App;
import crud.model.User;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationWithDbTests {
    User user = new User(1, "name", "email@mail.com", 32, LocalDate.now());

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    int port;

    @Test
    public void testCreate() {
        ResponseEntity<User> response = restTemplate.postForEntity(getRootUrl() + "/users", user, User.class);

        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusCode().value());
        Assert.assertEquals(user, response.getBody());

        User getUser = restTemplate.getForObject(getRootUrl() + "/users/" + user.getId(), User.class);

        Assert.assertEquals(user, getUser);
    }

    @Test
    public void testUpdate() {
        ResponseEntity<User> response = restTemplate.postForEntity(getRootUrl() + "/users", user, User.class);

        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusCode().value());
        Assert.assertEquals(user, response.getBody());

        user.setName("change name");
        user.setAge(33);
        restTemplate.put(getRootUrl() + "/users", user);

        User updateUser = restTemplate.getForObject(getRootUrl() + "/users/" + user.getId(), User.class);

        Assert.assertEquals(user, updateUser);
    }

    @Test
    public void testDelete() {
        ResponseEntity<User> response = restTemplate.postForEntity(getRootUrl() + "/users", user, User.class);

        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusCode().value());
        Assert.assertEquals(user, response.getBody());

        restTemplate.delete(getRootUrl() + "/users", user);

        User deleteUser = restTemplate.getForObject(getRootUrl() + "/users", User.class);

        Assert.assertEquals(null, deleteUser.getId());
        Assert.assertEquals(null, deleteUser.getName());
        Assert.assertEquals(null, deleteUser.getEmail());
        Assert.assertEquals(null, deleteUser.getAge());
        Assert.assertEquals(null, deleteUser.getDate());
    }

    String getRootUrl() {
        return "http://localhost:" + port;
    }
}
