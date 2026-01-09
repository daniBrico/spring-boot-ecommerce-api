package ecommerce_java_springboot.repositories;

import ecommerce_java_springboot.models.ProductModel;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductModel, Long> {

}
