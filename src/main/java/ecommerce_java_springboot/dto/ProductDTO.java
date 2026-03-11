package ecommerce_java_springboot.dto;

import ecommerce_java_springboot.models.enums.CategoryState;

import java.time.LocalDateTime;

public class ProductDTO {

  private Long id;
  private String name;
  private String description;
  private float price;
  private int stock;
  private CategoryState state;
  private LocalDateTime createdAt;
}
