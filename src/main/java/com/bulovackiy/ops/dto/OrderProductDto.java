package com.bulovackiy.ops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class OrderProductDto implements Serializable {

    private String sku;
    private String name;
    private BigDecimal qty;
    private BigDecimal price;
}
