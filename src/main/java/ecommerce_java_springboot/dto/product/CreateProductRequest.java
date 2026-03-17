package ecommerce_java_springboot.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank String name,
        @NotBlank String description,
        @NotNull BigDecimal price,
        @Positive int stock,
        @NotNull Long categoryId
) {}