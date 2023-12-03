package com.bulovackiy.ops.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductDto implements Serializable {

    private String id;
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal qty;
}
