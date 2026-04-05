package ecommerce_java_springboot.mappers.order;

import ecommerce_java_springboot.dto.order.OrderItemResponse;
import ecommerce_java_springboot.dto.order.OrderResponse;
import ecommerce_java_springboot.models.order.OrderItemModel;
import ecommerce_java_springboot.models.order.OrderModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public OrderResponse toResponse(OrderModel order) {
        List<OrderItemResponse> items = order.getItems().stream().map(this::toItemResponse).toList();

        return new OrderResponse(
                order.getId(),
                order.getTotal(),
                order.getState(),
                order.getCreatedAt(),
                items
        );
    }

    private OrderItemResponse toItemResponse(OrderItemModel item) {
        return new OrderItemResponse(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getUnits(),
                item.getUnitPrice(),
                item.getSubtotal()
        );
    }
}
