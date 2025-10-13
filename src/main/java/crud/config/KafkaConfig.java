package crud.config;

import org.apache.kafka.clients.admin.NewTopic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name("user-created-events-topic").build();
    }

    @Bean
    public NewTopic deleteTopic() {
        return TopicBuilder.name("user-deleted-events-topic").build();
    }
}
