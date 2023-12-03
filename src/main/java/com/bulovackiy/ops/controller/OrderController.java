package com.bulovackiy.ops.controller;

import com.bulovackiy.ops.dto.OrderDto;
import com.bulovackiy.ops.dto.OrderProductDto;
import com.bulovackiy.ops.service.OrderService;
import com.bulovackiy.ops.service.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping("/")
    public Flux<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Mono<OrderDto> getOrder(@PathVariable String id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/")
    public Mono<OrderDto> addOrder(@RequestBody OrderDto orderDto) {
        orderDto.setId(null);
        return orderService.createOrder(orderDto);
    }

    @PutMapping("/{id}")
    public Mono<OrderDto> updateOrder(@PathVariable String id,
                                      @RequestBody OrderDto orderDto) {
        orderDto.setId(id);
        return orderService.updateOrder(orderDto);
    }

    @PostMapping("/{id}/products")
    public Mono<Void> addProductToOrder(@PathVariable String id,
                                        @RequestBody List<OrderProductDto> productDtos) {
        return orderService.addProductToOrder(productDtos, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> removeOrder(@PathVariable String id) {
        return orderService.removeOrder(id);
    }
}
