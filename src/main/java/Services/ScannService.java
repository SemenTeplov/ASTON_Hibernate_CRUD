package Services;

import models.User;

import java.time.LocalDate;
import java.util.Scanner;

public class ScannService implements APIService {
    Scanner scan;
    String method;

    public ScannService() {
        scan = new Scanner(System.in);

        System.out.println("Выберите действие(1 - создать, 2 - прочитать, 3 - обновить, 4 - удалить): ");

        method = scan.next();
    }

    @Override
    public User create() {
        return get();
    }

    @Override
    public int read() {
        System.out.println("Введите идентификационный номер");

        return scan.nextInt();
    }

    @Override
    public User update() {
        return get();
    }

    @Override
    public User delete() {
        return get();
    }

    @Override
    public String getMethod() {
        return method;
    }

    private User get() {
        System.out.println("Введите идентификационный номер");
        int id = scan.nextInt();

        System.out.println("Введите имя");
        String name = scan.next();

        System.out.println("Введите email");
        String email = scan.next();

        System.out.println("Введите возраст");
        int age = scan.nextInt();

        return new User(id,name, email, age, LocalDate.now());
    }
}
