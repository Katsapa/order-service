package katsapa.service.orderservice;

public record Order (
        String orderId,
        String product,
        Integer quantity
){
}
