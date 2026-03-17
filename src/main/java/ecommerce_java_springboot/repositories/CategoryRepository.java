package ecommerce_java_springboot.repositories;

import ecommerce_java_springboot.models.CategoryModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryModel, Long> {

    Optional<CategoryModel> findByName(String name);
}
