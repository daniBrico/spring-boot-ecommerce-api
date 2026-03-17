package ecommerce_java_springboot.repositories;

import ecommerce_java_springboot.models.CartItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemModel, Long> {

}
