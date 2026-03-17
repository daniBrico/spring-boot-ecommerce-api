package ecommerce_java_springboot.dto.product;

import ecommerce_java_springboot.models.enums.ProductState;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductDTO(

        Long id,
        String name,
        String description,
        BigDecimal price,
        int stock,
        ProductState state,
        LocalDateTime createdAt
        ) {
}