package ecommerce_java_springboot.repositories;

import ecommerce_java_springboot.models.cart.CartItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemModel, Long> {
    Optional<CartItemModel> findByCartIdAndProductId(Long cartId, Long productId);
}
