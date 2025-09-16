import Services.ScannService;
import Services.UserService;
import database.PostgreSQLConnector;
import repositories.UsersRepository;

public class App {
    public static void main(String[] args) {
        Application application = new Application(new ScannService(), new UserService(new UsersRepository(new PostgreSQLConnector())));

        application.run();
    }
}
