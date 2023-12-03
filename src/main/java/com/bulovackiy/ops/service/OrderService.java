package com.bulovackiy.ops.service;

import com.bulovackiy.ops.dto.OrderDto;
import com.bulovackiy.ops.dto.OrderProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OrderService {

    Flux<OrderDto> getAllOrders();
    Mono<OrderDto> getOrder(String id);
    Mono<OrderDto> createOrder(OrderDto orderDto);
    Mono<OrderDto> updateOrder(OrderDto orderDto);
    Mono<Void> removeOrder(String id);
    Mono<Void> addProductToOrder(List<OrderProductDto> productDtos, String orderId);
    Mono<Void> removeProductFromOrder(List<OrderProductDto> productDtos, String orderId);
}
