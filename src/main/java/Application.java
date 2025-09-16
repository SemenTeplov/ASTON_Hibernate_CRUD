import Services.APIService;
import Services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Application {
    private final APIService apiService;
    private final UserService userService;

    public void run() {
        switch (apiService.getMethod()) {
            case "1" -> System.out.println(userService.create(apiService.create()));
            case "2" -> System.out.println(userService.read(apiService.read()));
            case "3" -> System.out.println(userService.update(apiService.update()));
            case "4" -> System.out.println(userService.delete(apiService.delete()));
            default -> System.out.println("Введено неизвестное значение");
        }
    }
}
