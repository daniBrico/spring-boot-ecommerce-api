package ecommerce_java_springboot.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddCartItemRequest(
        @NotNull Long productId,

        @Positive @Min(value = 1, message = "Units must be at least 1")
        int units
) {
}
