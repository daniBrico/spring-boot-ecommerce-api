package ecommerce_java_springboot.models;

import ecommerce_java_springboot.models.enums.CategoryState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryState state;

    @OneToMany(
            mappedBy = "category",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private List<ProductModel> products = new ArrayList<>();

    public void addProduct(ProductModel product) {
        products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(ProductModel product) {
        products.remove(product);
        product.setCategory(null);
    }
}
