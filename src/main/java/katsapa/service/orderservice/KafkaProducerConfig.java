package katsapa.service.orderservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import tools.jackson.databind.ObjectMapper;


import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaProducerConfig {
    @Bean
    public ProducerFactory<String, Order> producerFactory(ObjectMapper objectMapper) {
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        Serializer<Order> valueSerializer = (topic, data) -> {
            if (data == null) return null;
            try {
                return objectMapper.writeValueAsBytes(data);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка сериализации Order", e);
            }
        };

        return new DefaultKafkaProducerFactory<>(
                configProperties,
                new StringSerializer(),
                valueSerializer
        );
    }

    @Bean
    public KafkaTemplate<String, Order> kafkaTemplate(
        ProducerFactory<String, Order> producerFactory
    ){
        return new KafkaTemplate<>(producerFactory);
    }
}
