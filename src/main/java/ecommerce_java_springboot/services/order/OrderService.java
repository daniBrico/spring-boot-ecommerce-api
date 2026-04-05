package ecommerce_java_springboot.services.order;

import ecommerce_java_springboot.common.exception.CartEmptyException;
import ecommerce_java_springboot.common.exception.OutOfStockException;
import ecommerce_java_springboot.common.exception.ResourceNotFoundException;
import ecommerce_java_springboot.models.UserModel;
import ecommerce_java_springboot.models.cart.CartItemModel;
import ecommerce_java_springboot.models.cart.CartModel;
import ecommerce_java_springboot.models.enums.OrderState;
import ecommerce_java_springboot.models.order.OrderItemModel;
import ecommerce_java_springboot.models.order.OrderModel;
import ecommerce_java_springboot.models.product.ProductModel;
import ecommerce_java_springboot.repositories.CartRepository;
import ecommerce_java_springboot.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public OrderModel checkout(UserModel user) {
        CartModel cart = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new CartEmptyException("Cart is empty");
        }

        for  (CartItemModel cartItem : cart.getItems()) {
            if (cartItem.getUnits() > cartItem.getProduct().getStock()) {
                throw new OutOfStockException("Unit is out of stock");
            }
        }

        OrderModel order = new OrderModel();
        order.setUser(user);
        order.setState(OrderState.CREATED);

        List<OrderItemModel> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (CartItemModel cartItem : cart.getItems()) {
            ProductModel product = cartItem.getProduct();

            product.setStock(product.getStock() - cartItem.getUnits());

            BigDecimal itemTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getUnits()));

            total = total.add(itemTotal);

            OrderItemModel orderItem = OrderItemModel.builder()
                    .order(order)
                    .product(product)
                    .units(cartItem.getUnits())
                    .unitPrice(product.getPrice())
                    .build();

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotal(total);

        OrderModel savedOrder = orderRepository.save(order);

        cart.getItems().clear();
        cartRepository.save(cart);

        return savedOrder;
    }
}
