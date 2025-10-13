package crud.config;

import crud.manager.SimpleOrderManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.yandex.com");
        mailSender.setPort(587);
        mailSender.setProtocol("smtp");
        mailSender.setUsername("Name");
        mailSender.setPassword("Password");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateMessage() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("some-mail@mail.ru");
        message.setSubject("Example");

        return message;
    }

    @Bean
    public SimpleOrderManager orderManager(JavaMailSender mailSender, SimpleMailMessage templateMessage) {
        SimpleOrderManager orderManager = new SimpleOrderManager(mailSender, templateMessage);

        return orderManager;
    }
}
