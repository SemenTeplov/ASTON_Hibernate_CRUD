package crud.dal;

import crud.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbc;
    private final UserMapper mapper;

    private static final String CREATE_QUERY =
            "INSERT INTO users (id, name, email, age, created_at) VALUES ('%d', '%s', '%s', '%d', '%s')";
    private static final String UPDATE_QUERY =
            "UPDATE users SET name = '%s', email = '%s', age = '%d', created_at = '%s' WHERE id = '%d'";
    private static final String READ_QUERY =
            "SELECT * FROM users WHERE id = '%d'";
    private static final String DELETE_QUERY =
            "DELETE FROM users WHERE id = '%d'";

    public User create(User user) {
        jdbc.execute(String.format(
                CREATE_QUERY, user.getId(), user.getName(), user.getEmail(), user.getAge(), LocalDate.now()));

        return user;
    }

    public User update(User user) {
        jdbc.execute(String.format(
                UPDATE_QUERY, user.getName(), user.getEmail(), user.getAge(), LocalDate.now(), user.getId()));

        return user;
    }

    public User read(Integer id) {
        return jdbc.query(String.format(READ_QUERY, id), mapper).getFirst();
    }

    public User delete(User user) {
        jdbc.execute(String.format(DELETE_QUERY, user.getId()));

        return user;
    }
}
