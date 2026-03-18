package ecommerce_java_springboot.mappers;


import ecommerce_java_springboot.dto.category.CategoryDTO;
import ecommerce_java_springboot.models.CategoryModel;

public class CategoryMapper {

    public static CategoryDTO toCategoryDTO(CategoryModel category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getState()
        );
    }
}
