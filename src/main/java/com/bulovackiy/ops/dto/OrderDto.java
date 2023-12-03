package com.bulovackiy.ops.dto;

import com.bulovackiy.ops.repository.entity.OrderProduct;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDto implements Serializable {

    private String id;
    private String orderNumber;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;
    private List<OrderProductDto> products;

}
