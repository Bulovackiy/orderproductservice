package com.bulovackiy.ops.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderProduct implements Serializable {

    private String sku;
    private String name;
    private LocalDateTime created;
    private BigDecimal qty;
    private BigDecimal price;
}
