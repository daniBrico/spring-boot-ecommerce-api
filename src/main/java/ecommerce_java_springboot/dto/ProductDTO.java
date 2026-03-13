package ecommerce_java_springboot.dto;

import ecommerce_java_springboot.models.enums.CategoryState;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private float price;
    private int stock;
    private CategoryState state;
    private LocalDateTime createdAt;
}
