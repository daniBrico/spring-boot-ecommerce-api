package ecommerce_java_springboot.mappers;

import ecommerce_java_springboot.dto.product.ProductDTO;
import ecommerce_java_springboot.models.ProductModel;

public class ProductMapper {

    public static ProductDTO mapToDTO(ProductModel product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getState(),
                product.getCreatedAt()
        );
    }
}
