import models.User;

import repositories.UsersRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        UsersRepository repository = new UsersRepository();

        System.out.println("Выберите действие(1 - создать, 2 - прочитать, 3 - обновить, 4 - удалить): ");
        String input = scan.next();

        switch (input) {
            case "1" -> {
                System.out.println("Введите идентификационный номер");
                int id = scan.nextInt();

                System.out.println("Введите имя");
                String name = scan.next();

                System.out.println("Введите email");
                String email = scan.next();

                System.out.println("Введите возраст");
                int age = scan.nextInt();

                repository.create(new User(id,name, email, age, LocalDate.now()));
            }
            case "2" -> {
                System.out.println("Введите идентификационный номер");
                int id = scan.nextInt();

                System.out.println(repository.read(id));
            }
            case "3" -> {
                System.out.println("Введите идентификационный номер");
                int id = scan.nextInt();

                System.out.println("Введите имя");
                String name = scan.next();

                System.out.println("Введите email");
                String email = scan.next();

                System.out.println("Введите возраст");
                int age = scan.nextInt();

                repository.update(new User(id,name, email, age, LocalDate.now()));
            }
            case "4" -> {
                System.out.println("Введите идентификационный номер");
                int id = scan.nextInt();

                System.out.println("Введите имя");
                String name = scan.next();

                System.out.println("Введите email");
                String email = scan.next();

                System.out.println("Введите возраст");
                int age = scan.nextInt();

                repository.delete(new User(id,name, email, age, LocalDate.now()));
            }
            default -> {
                System.out.println("Введено неизвестное значение");
            }
        }
    }
}
