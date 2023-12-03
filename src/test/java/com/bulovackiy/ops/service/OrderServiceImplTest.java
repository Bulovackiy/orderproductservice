package com.bulovackiy.ops.service;

import com.bulovackiy.ops.dto.OrderDto;
import com.bulovackiy.ops.dto.OrderProductDto;
import com.bulovackiy.ops.repository.OrderRepository;
import com.bulovackiy.ops.repository.entity.Order;
import com.bulovackiy.ops.repository.entity.OrderProduct;
import com.bulovackiy.ops.repository.entity.Product;
import com.bulovackiy.ops.utils.MappingHelper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.given;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;


@SpringBootTest
class OrderServiceImplTest {

    private static final LocalDateTime created = LocalDateTime.of(2023, 12, 1, 12, 30, 55);
    private static final LocalDateTime updated = LocalDateTime.of(2023, 12, 2, 12, 30, 55);

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void givenOrderList_whenGetAllOrders_thenReturnAllOrdersDto() {
        OrderDto[] expectedDtos = createOrderDtos();

        given(orderRepository.findAll()).willReturn(Flux.just(createOrders()));

        Flux<OrderDto> dtos = orderService.getAllOrders();

        StepVerifier.create(dtos)
                .assertNext(entry -> assertThat(entry).isEqualTo(expectedDtos[0]))
                .assertNext(entry -> assertThat(entry).isEqualTo(expectedDtos[1]))
                .verifyComplete();
    }

    @Test
    void givenOrder_whenGetOrder_thenReturnOrderDto() {
        OrderDto[] expectedDtos = createOrderDtos();

        given(orderRepository.findById(anyString())).willReturn(Mono.just(createOrders()[0]));

        Mono<OrderDto> result = orderService.getOrder("some_id");

        StepVerifier.create(result)
                .assertNext(entry -> assertThat(entry).isEqualTo(expectedDtos[0]))
                .verifyComplete();
    }

    @Test
    void whenGetOrder_thenReturnEmptyResult() {
        given(orderRepository.findById(anyString())).willReturn(Mono.empty());

        Mono<OrderDto> result = orderService.getOrder("some_id");

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void givenOrder_whenCreateOrder_thenReturnCreatedOrderDto() {
        OrderDto[] expected = createOrderDtos();
        given(orderRepository.insert(any(Order.class))).willReturn(Mono.just(createOrders()[0]));

        Mono<OrderDto> result = orderService.createOrder(expected[0]);

        StepVerifier.create(result)
                .assertNext(entry -> assertThat(entry).isEqualTo(expected[0]))
                .verifyComplete();
    }

    @Test
    void givenOrder_whenUpdateOrder_thenReturnUpdatedOrderDto() {
        OrderDto[] expected = createOrderDtos();
        given(orderRepository.save(any(Order.class))).willReturn(Mono.just(createOrders()[0]));

        Mono<OrderDto> result = orderService.updateOrder(expected[0]);

        StepVerifier.create(result)
                .assertNext(entry -> assertThat(entry).isEqualTo(expected[0]))
                .verifyComplete();
    }

    @Test
    void removeOrder() {
        given(orderRepository.deleteById(anyString())).willReturn(Mono.empty().then());

        Mono<Void> result = orderService.removeOrder("some_id");

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void addProductToOrder() {
        List<OrderProductDto> products = createProductsDto();

        given(orderRepository.pushOrderProduct(anyList(), anyString(), any(LocalDateTime.class)))
                .willReturn(Mono.empty().then());

        Mono<Void> result = orderService.addProductToOrder(products, "order_id");

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void removeProductFromOrder() {
    }

    private OrderDto[] createOrderDtos() {
        OrderDto order1 = OrderDto.builder()
                .id("qqqqq1111")
                .created(created)
                .updated(updated)
                .orderNumber("1")
                .products(createProductsDto())
                .build();

        OrderDto order2 = OrderDto.builder()
                .id("qqqqq2222")
                .created(created)
                .updated(updated)
                .orderNumber("2")
                .products(createProductsDto())
                .build();

        return new OrderDto[]{order1, order2};
    }

    private Order[] createOrders() {
        Order order1 = Order.builder()
                .id("qqqqq1111")
                .created(created)
                .updated(updated)
                .orderNumber("1")
                .products(createProducts())
                .build();

        Order order2 = Order.builder()
                .id("qqqqq2222")
                .created(created)
                .updated(updated)
                .orderNumber("2")
                .products(createProducts())
                .build();

        return new Order[]{order1, order2};
    }

    private List<OrderProduct> createProducts() {
        OrderProduct op1 = OrderProduct.builder()
                .name("product_1")
                .created(created)
                .price(BigDecimal.valueOf(10))
                .qty(BigDecimal.valueOf(100))
                .sku("sku_1")
                .build();

        OrderProduct op2 = OrderProduct.builder()
                .name("product_2")
                .created(created)
                .price(BigDecimal.valueOf(20))
                .qty(BigDecimal.valueOf(200))
                .sku("sku_2")
                .build();

        return List.of(op1, op2);
    }

    private List<OrderProductDto> createProductsDto() {
        OrderProductDto op1 = OrderProductDto.builder()
                .name("product_1")
                .price(BigDecimal.valueOf(10))
                .qty(BigDecimal.valueOf(100))
                .sku("sku_1")
                .build();

        OrderProductDto op2 = OrderProductDto.builder()
                .name("product_2")
                .price(BigDecimal.valueOf(20))
                .qty(BigDecimal.valueOf(200))
                .sku("sku_2")
                .build();

        return List.of(op1, op2);
    }
}