package com.bulovackiy.ops.controller;

import com.bulovackiy.ops.dto.ProductDto;
import com.bulovackiy.ops.service.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductServiceImpl productService;

    @GetMapping("/")
    public Flux<ProductDto> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProductById(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @PostMapping("/")
    public Mono<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        return productService.createProduct(productDto);
    }

    @PutMapping("/{id}")
    public Mono<ProductDto> updateProduct(@PathVariable String id,
                                          @RequestBody ProductDto productDto) {
        productDto.setId(id);
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/api/products/{id}")
    public Mono<Void> removeProduct(@PathVariable String id) {
        return productService.removeProduct(id);
    }
}
