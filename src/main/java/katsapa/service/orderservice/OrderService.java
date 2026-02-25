package katsapa.service.orderservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderKafkaProducer orderKafkaProducer;

    public void saveOrder(Order order) {
        orderKafkaProducer.sendOrderToKafka(order);

        log.info("Order successfully save: id={}", order.orderId());
    }
}
