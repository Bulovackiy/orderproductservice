package com.bulovackiy.ops.service;

import com.bulovackiy.ops.dto.ProductDto;
import com.bulovackiy.ops.repository.ProductRepository;
import com.bulovackiy.ops.repository.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public Mono<ProductDto> createProduct(ProductDto productDto) {
        Product product = dtoToEntity(productDto);
        return productRepository.insert(product).map(this::entityToDto);
    }

    public Mono<ProductDto> updateProduct(ProductDto productDto) {
        Product product = dtoToEntity(productDto);
        return productRepository.save(product).map(this::entityToDto);
    }

    public Mono<ProductDto> getProduct(String id) {
        return productRepository.findById(id)
                .map(this::entityToDto);
    }

    public Flux<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .map(this::entityToDto);
    }

    public Mono<Void> removeProduct(String id) {
        return productRepository.deleteById(id);
    }

    private Product dtoToEntity(ProductDto dto) {
        Product product = Product.builder().build();
        BeanUtils.copyProperties(dto, product);
        return product;
    }

    private ProductDto entityToDto(Product product) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }
}
