package crud.manager;

import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@Slf4j
public class SimpleOrderManager implements OrderManager {
    private final MailSender mailSender;
    private final SimpleMailMessage templateMessage;

    public SimpleOrderManager(MailSender mailSender, SimpleMailMessage templateMessage) {
        this.mailSender = mailSender;
        this.templateMessage = templateMessage;
    }

    @Override
    public void placeOrder(String email, String text) {
        SimpleMailMessage msg = new SimpleMailMessage(templateMessage);

        msg.setTo(email);
        msg.setText(text);

        try {
            mailSender.send(msg);
        } catch (MailException e) {
            log.error(e.getMessage());
        }
    }
}
