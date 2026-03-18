package ecommerce_java_springboot.dto.cart;

import java.math.BigDecimal;

public record CartItemResponse(
        Long productId,
        String productName,
        int units,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {
}
