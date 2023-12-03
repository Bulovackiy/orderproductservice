package com.bulovackiy.ops.repository.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
@Builder
public class Order implements Serializable {

    @MongoId
    private String id;
    private String orderNumber;
    private LocalDateTime created;
    private LocalDateTime updated;
    private List<OrderProduct> products;
}
