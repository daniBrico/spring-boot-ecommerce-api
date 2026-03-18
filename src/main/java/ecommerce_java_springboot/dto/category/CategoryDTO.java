package ecommerce_java_springboot.dto.category;

import ecommerce_java_springboot.models.enums.CategoryState;

public record CategoryDTO (
    Long id,
    String name,
    String description,
    CategoryState state
) {
}
