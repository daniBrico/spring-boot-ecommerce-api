package ecommerce_java_springboot.dto.order;

import ecommerce_java_springboot.models.enums.OrderState;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        BigDecimal total,
        OrderState state,
        LocalDateTime createdAt,
        List<OrderItemResponse> items
) {

}
