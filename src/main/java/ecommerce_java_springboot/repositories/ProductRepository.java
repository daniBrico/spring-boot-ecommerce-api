package ecommerce_java_springboot.repositories;

import ecommerce_java_springboot.models.product.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
//    Optional<ProductModel> findById(Long id);
}
