package katsapa.service.orderservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {
    public void saveOrder(Order order) {
        log.info("Order successfully save: id={}", order.orderId());

    }
}
