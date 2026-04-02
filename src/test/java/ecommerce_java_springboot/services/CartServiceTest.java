package ecommerce_java_springboot.services;

import ecommerce_java_springboot.dto.cart.CartResponse;
import ecommerce_java_springboot.dto.request.AddCartItemRequest;
import ecommerce_java_springboot.mappers.CartMapper;
import ecommerce_java_springboot.models.cart.CartItemModel;
import ecommerce_java_springboot.models.cart.CartModel;
import ecommerce_java_springboot.models.product.ProductModel;
import ecommerce_java_springboot.models.UserModel;
import ecommerce_java_springboot.repositories.CartItemRepository;
import ecommerce_java_springboot.repositories.CartRepository;
import ecommerce_java_springboot.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private AuthService authService;

    @Mock
    private CartMapper cartMapper;

    @InjectMocks
    private CartService cartService;

    @Test
    void shouldAddProductToCart(){
        ProductModel product = new ProductModel();
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(100));
        product.setStock(10);

        UserModel user = new UserModel();
        user.setId(1L);

        CartModel cart = new CartModel();
        cart.setUser(user);
        cart.setId(1L);

        AddCartItemRequest request = new AddCartItemRequest(1L, 2);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(authService.getCurrentUser()).thenReturn(user);
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(cartItemRepository.findByCartIdAndProductId(1L,1L)).thenReturn(Optional.empty());
        when(cartMapper.toResponse(any(CartModel.class))).thenReturn(new CartResponse(1L, List.of(), BigDecimal.valueOf(200)));

        cartService.addItem(request);

        verify(cartItemRepository).save(any(CartItemModel.class));
    }
}
