package com.bulovackiy.ops.service;

import com.bulovackiy.ops.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<ProductDto> createProduct(ProductDto productDto);

    Mono<ProductDto> updateProduct(ProductDto productDto);

    Mono<ProductDto> getProduct(String id);

    Flux<ProductDto> getAllProducts();

    Mono<Void> removeProduct(String id);

}
