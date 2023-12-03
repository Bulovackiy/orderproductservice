package com.bulovackiy.ops.repository.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Data
@Builder
public class Product {

    @MongoId
    private String id;
    private String sku;
    private String name;
    private String description;
    private LocalDateTime created;
    private BigDecimal price;
    private BigDecimal qty;

}
