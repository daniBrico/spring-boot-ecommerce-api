package ecommerce_java_springboot.services;

import ecommerce_java_springboot.common.exception.CartEmptyException;
import ecommerce_java_springboot.common.exception.OutOfStockException;
import ecommerce_java_springboot.common.exception.ResourceNotFoundException;
import ecommerce_java_springboot.models.UserModel;
import ecommerce_java_springboot.models.cart.CartItemModel;
import ecommerce_java_springboot.models.cart.CartModel;
import ecommerce_java_springboot.models.order.OrderModel;
import ecommerce_java_springboot.models.product.ProductModel;
import ecommerce_java_springboot.repositories.CartRepository;
import ecommerce_java_springboot.repositories.OrderRepository;
import ecommerce_java_springboot.services.order.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private UserModel user;

    @BeforeEach
    void setUp() {
        user = new UserModel();
        user.setId(1L);
    }

    @Test
    void shouldCreateOrderSuccessfully() {
        ProductModel product = new ProductModel();
        product.setStock(10);
        product.setPrice(new BigDecimal("100"));

        CartItemModel cartItem = new CartItemModel();
        cartItem.setProduct(product);
        cartItem.setUnits(2);

        CartModel cart = new CartModel();
        cart.setItems(List.of(cartItem));

        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(orderRepository.save(any(OrderModel.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        OrderModel result = orderService.checkout(user);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getItems().size());

        // Correct total
        assertEquals(new BigDecimal("200"), result.getTotal());

        // Updated stock
        assertEquals(8, product.getStock());

        // Empty cart
        assertTrue(cart.getItems().isEmpty());

        verify(orderRepository).save(any(OrderModel.class));
        verify(cartRepository).save(cart);
    }

    @Test
    void shouldThrowExceptionWhenCartNotFound() {
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.checkout(user));

        verify(orderRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenCartIsEmpty() {
        CartModel cart = new CartModel();
        cart.setItems(new ArrayList<>());

        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));

        assertThrows(CartEmptyException.class, () -> orderService.checkout(user));
    }

    @Test
    void shouldThrowExceptionWhenOutOfStock() {
        ProductModel product = new ProductModel();
        product.setStock(1);

        CartItemModel cartItem = new CartItemModel();
        cartItem.setProduct(product);
        cartItem.setUnits(5);

        CartModel cart = new CartModel();
        cart.setItems(List.of(cartItem));

        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));

        assertThrows(OutOfStockException.class, () -> orderService.checkout(user));
    }
}
