package ecommerce_java_springboot.repositories;

import ecommerce_java_springboot.models.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartModel, Long> {
    Optional<CartModel> findByUserId(Long userId);
}
