package ecommerce_java_springboot.dto.product;

import jakarta.validation.constraints.NotBlank;

public record GetProductRequest(
        @NotBlank Long id
) {
}
