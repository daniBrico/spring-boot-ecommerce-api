package ecommerce_java_springboot.mappers;

import ecommerce_java_springboot.dto.ProductDTO;
import ecommerce_java_springboot.models.ProductModel;

public class ProductMapper {

    public static ProductDTO toDto(ProductModel product) {

        ProductDTO dto = new ProductDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());

        return dto;
    }
}
