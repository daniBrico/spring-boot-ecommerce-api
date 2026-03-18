package ecommerce_java_springboot.services;

import ecommerce_java_springboot.common.exception.ResourceNotFoundException;
import ecommerce_java_springboot.dto.request.AddCartItemRequest;
import ecommerce_java_springboot.models.CartItemModel;
import ecommerce_java_springboot.models.CartModel;
import ecommerce_java_springboot.models.ProductModel;
import ecommerce_java_springboot.models.UserModel;
import ecommerce_java_springboot.repositories.CartItemRepository;
import ecommerce_java_springboot.repositories.CartRepository;
import ecommerce_java_springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private AuthService authService;

    public void addItem(AddCartItemRequest request) {
        ProductModel product = productRepository.findById(request.productId()).orElseThrow(() -> new ResourceNotFoundException("product not found"));

        CartModel cart = getCurrentUserCart();
        CartItemModel cartItem = cartRepository.findByCartIdAndProductId(cart.getId(), product.getId()).orElse(null);

        if (cartItem != null) {
            cartItem.setUnits(cartItem.getUnits() + request.units());
        } else {
            cartItem = new CartItemModel();
            cartItem.setProduct(product);
            cartItem.setUnits(request.units());
            cartItem.setUnitPrice(product.getPrice());
            cartItem.setCart(cart);
        }

        cartItemRepository.save(cartItem);
    }

    private CartModel getCurrentUserCart() {
        UserModel user = authService.getCurrentUser();

        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    CartModel newCart = new CartModel();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    }
}
