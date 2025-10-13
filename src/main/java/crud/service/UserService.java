package crud.service;

import crud.model.User;
import crud.storage.UserStorage;

import lombok.extern.slf4j.Slf4j;

import crud.manager.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class UserService {
    private final UserStorage storage;
    private final KafkaTemplate<Integer, String> template;
    private final OrderManager orderManager;

    @Autowired
    public UserService(UserStorage storage, KafkaTemplate<Integer, String> template, OrderManager orderManager) {
        this.storage = storage;
        this.template = template;
        this.orderManager = orderManager;
    }

    public User create(User user) {
        CompletableFuture<SendResult<Integer, String>> future =
                template.send("user-created-events-topic", user.getId(), user.getEmail());

        future.whenComplete((result, exception) -> {
            if (exception == null) {
                log.info("message sent successfully: {}", result.getProducerRecord().value());
            } else {
                log.error("something wrong: {}", exception.getMessage());
            }
        });

        log.info("User {} created", user);

        return storage.create(user);
    }

    public User createEmail(User user) {
        String createdText = "Здравствуйте! Ваш аккаунт на сайте ваш сайт был успешно создан.";

        orderManager.placeOrder(user.getEmail(), createdText);

        log.info("User {} created", user);

        return storage.create(user);
    }

    public User read(Integer id) {
        log.info("Got user");
        return storage.read(id);
    }

    public User update(User user) {
        log.info("User updated");

        return storage.update(user);
    }

    public User delete(User user) {
        CompletableFuture<SendResult<Integer, String>> future =
                template.send("user-deleted-events-topic", user.getId(), user.getEmail());

        future.whenComplete((result, exception) -> {
            if (exception == null) {
                log.info("message sent successfully: {}", result.getProducerRecord().value());
            } else {
                log.error("something wrong: {}", exception.getMessage());
            }
        });

        log.info("User {} deleted", user);

        return storage.delete(user);
    }

    public User deleteEmail(User user) {
        String deletedText = "Здравствуйте! Ваш аккаунт был удалён.";

        orderManager.placeOrder(user.getEmail(), deletedText);

        log.info("User {} deleted", user);

        return storage.delete(user);
    }
}
