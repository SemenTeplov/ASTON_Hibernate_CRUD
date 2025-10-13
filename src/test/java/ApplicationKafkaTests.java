import crud.model.User;
import crud.service.UserService;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import org.apache.kafka.streams.StreamsConfig;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = {"user-created-events-topic", "user-deleted-events-topic"})
public class ApplicationKafkaTests {
    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;

    @Autowired
    UserService service;

    @Test
    void testCreateUser_validUserDetails_successfullySendsKafkaMessage() throws InterruptedException {
        User user = new User(
                1,
                "exampe name",
                "some-mail@mail.com",
                50,
                LocalDate.of(1990, 5, 5));

        Map<String, Object> consumerProps =
                KafkaTestUtils.consumerProps("user-created-events", "true", this.embeddedKafka);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        DefaultKafkaConsumerFactory<Integer, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);

        Consumer<Integer, String> consumer = cf.createConsumer();
        this.embeddedKafka.consumeFromAnEmbeddedTopic(consumer, "user-created-events-topic");

        service.create(user);

        ConsumerRecord<Integer, String> record = new LinkedBlockingQueue<ConsumerRecord<Integer, String>>().poll(300, TimeUnit.MILLISECONDS);

        assertNotNull(record);
        assertNotNull(record.key());
        assertEquals(record.value(), user.getEmail());
    }

    @Test
    void testDeleteUser_validUserDetails_successfullySendsKafkaMessage() throws InterruptedException {
        User user = new User(
                1,
                "exampe name",
                "some-mail@mail.com",
                50,
                LocalDate.of(1990, 5, 5));

        Map<String, Object> consumerProps =
                KafkaTestUtils.consumerProps("user-created-events", "true", this.embeddedKafka);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        DefaultKafkaConsumerFactory<Integer, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);

        Consumer<Integer, String> consumer = cf.createConsumer();
        this.embeddedKafka.consumeFromAnEmbeddedTopic(consumer, "user-created-events-topic");

        service.delete(user);

        ConsumerRecord<Integer, String> record = new LinkedBlockingQueue<ConsumerRecord<Integer, String>>().poll(300, TimeUnit.MILLISECONDS);

        assertNotNull(record);
        assertNotNull(record.key());
        assertEquals(record.value(), user.getEmail());
    }

    @Configuration
    @EnableKafkaStreams
    public static class KafkaConfig  {
        @Value("${" + EmbeddedKafkaBroker.SPRING_EMBEDDED_KAFKA_BROKERS + "}")
        private String brokerAddresses;

        @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
        public KafkaStreamsConfiguration kStreamsConfigs() {
            Map<String, Object> props = new HashMap<>();

            props.put(StreamsConfig.APPLICATION_ID_CONFIG, "user-created-events");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
            props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, StringDeserializer.class);
            props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddresses);

            return new KafkaStreamsConfiguration(props);
        }

    }
}
