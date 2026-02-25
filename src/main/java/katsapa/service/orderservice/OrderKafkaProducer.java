package katsapa.service.orderservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderKafkaProducer {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public void sendOrderToKafka(Order order){
        kafkaTemplate.send("orders", order);
        log.info("order send to kafka: id={}", order.orderId());
    }
}
