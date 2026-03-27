package ecommerce_java_springboot.models;

import ecommerce_java_springboot.models.enums.ProductState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryModel category;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
