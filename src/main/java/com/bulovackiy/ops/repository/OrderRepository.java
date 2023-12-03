package com.bulovackiy.ops.repository;

import com.bulovackiy.ops.repository.entity.Order;
import com.bulovackiy.ops.repository.entity.OrderProduct;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

    @Query("{ 'id' :  ?1}")
    @Update("{'$set' :  {'updated' : ?2}, '$push': { 'products':  { $each :  ?0 }} }")
    Mono<Void> pushOrderProduct(List<OrderProduct> products, String orderId, LocalDateTime updated);

}
