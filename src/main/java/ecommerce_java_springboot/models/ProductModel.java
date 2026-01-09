package ecommerce_java_springboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String name;

  private String description;

  @Column(nullable = false)
  private float price;

  @Column(nullable = false)
  private int stock;

  @Column(nullable = false)
  private Boolean state;

  @NotNull
  @Column(nullable = false)
  private String category;

  @NotNull
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }
}
