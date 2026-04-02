package ecommerce_java_springboot.mappers;

import ecommerce_java_springboot.dto.cart.CartItemResponse;
import ecommerce_java_springboot.dto.cart.CartResponse;
import ecommerce_java_springboot.models.cart.CartItemModel;
import ecommerce_java_springboot.models.cart.CartModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CartMapper {

    public CartResponse toResponse(CartModel cart) {
        List<CartItemResponse> items = cart.getItems().stream().map(this::toItemsResponse).toList();

        BigDecimal total = items.stream().map(CartItemResponse::subtotal).reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartResponse(cart.getId(), items, total);
    }

    public CartItemResponse toItemsResponse(CartItemModel item) {
        return new CartItemResponse(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getUnits(),
                item.getUnitPrice(),
                item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits()))
        );
    }
}
