package ecommerce_java_springboot.repositories;

import ecommerce_java_springboot.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

}
