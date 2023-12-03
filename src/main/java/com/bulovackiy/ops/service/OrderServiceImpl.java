package com.bulovackiy.ops.service;

import com.bulovackiy.ops.dto.OrderDto;
import com.bulovackiy.ops.dto.OrderProductDto;
import com.bulovackiy.ops.repository.OrderRepository;
import com.bulovackiy.ops.repository.entity.Order;
import com.bulovackiy.ops.repository.entity.OrderProduct;
import com.bulovackiy.ops.utils.MappingHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Override
    public Flux<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .map(this::orderToOrderDto);
    }

    @Override
    public Mono<OrderDto> getOrder(String id) {
        return orderRepository.findById(id)
                .map(this::orderToOrderDto);
    }

    @Override
    public Mono<OrderDto> createOrder(OrderDto orderDto) {
        LocalDateTime now = LocalDateTime.now();
        orderDto.setCreated(now);
        orderDto.setUpdated(now);

        Order order = MappingHelper.copyClass(orderDto, Order.builder().build());

        order.getProducts().forEach(op -> op.setCreated(now));

        return orderRepository.insert(order)
                .map(this::orderToOrderDto);
    }

    @Override
    public Mono<OrderDto> updateOrder(OrderDto orderDto) {
        orderDto.setUpdated(LocalDateTime.now());
        Order order = MappingHelper.copyClass(orderDto, Order.builder().build());
        return orderRepository.save(order)
                .map(this::orderToOrderDto);
    }

    @Override
    public Mono<Void> removeOrder(String id) {
        return orderRepository.deleteById(id);
    }

    @Override
    public Mono<Void> addProductToOrder(List<OrderProductDto> productDtos, String orderId) {
        List<OrderProduct> products = productDtos.stream()
                .map(dto -> MappingHelper.copyClass(dto, OrderProduct.builder().build()))
                .toList();

        return orderRepository.pushOrderProduct(products, orderId, LocalDateTime.now());
    }

    @Override
    public Mono<Void> removeProductFromOrder(List<OrderProductDto> productDtos, String orderId) {


        return null;
    }

    private OrderDto orderToOrderDto(Order order){
        OrderDto dto = MappingHelper.copyClass(order, OrderDto.builder().build());
        dto.setProducts(order.getProducts().stream()
                .map(p -> MappingHelper.copyClass(p, OrderProductDto.builder().build()))
                .toList());

        return dto;
    }
}
