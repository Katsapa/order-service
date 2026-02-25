package katsapa.service.orderservice;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService service;
    private final AtomicInteger orderIdCounter = new AtomicInteger();


    @PostMapping
    public void createOrder(@RequestBody Order order){
        log.info("create order called: order={}", order);
        int orderId = orderIdCounter.incrementAndGet();

        var productName = order.product() + ThreadLocalRandom.current().nextInt(100);

        var orderToSave = new Order(
                Integer.toString(orderId),
                productName,
                order.quantity()
        );

        service.saveOrder(orderToSave);
    }
}
