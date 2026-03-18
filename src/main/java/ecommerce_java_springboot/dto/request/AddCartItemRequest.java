package ecommerce_java_springboot.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddCartItemRequest(
        @NotNull Long productId,
        @Positive int units
) {
}
