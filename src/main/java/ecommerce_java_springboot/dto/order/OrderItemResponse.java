package ecommerce_java_springboot.dto.order;

import java.math.BigDecimal;

public record OrderItemResponse(
    Long productId,
    String productName,
    Integer units,
    BigDecimal unitPrice,
    BigDecimal subtotal
) {
}
