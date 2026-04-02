package ecommerce_java_springboot.config;

import ecommerce_java_springboot.models.product.CategoryModel;
import ecommerce_java_springboot.models.product.ProductModel;
import ecommerce_java_springboot.models.enums.CategoryState;
import ecommerce_java_springboot.models.enums.ProductState;
import ecommerce_java_springboot.repositories.CategoryRepository;
import ecommerce_java_springboot.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedCategories(CategoryRepository categoryRepository) {
        return args -> {
            if (categoryRepository.count() == 0) {
                CategoryModel electronics = createCategory("Electronics", "Electronic devices and accessories");
                categoryRepository.save(electronics);

                CategoryModel books = createCategory("Books", "Books and literature");
                categoryRepository.save(books);

                CategoryModel clothing = createCategory("Clothing", "Clothing and apparel");
                categoryRepository.save(clothing);
            }
        };
    }

    @Bean
    CommandLineRunner seedProducts(ProductRepository productRepository, CategoryRepository categoryRepository) {

        return args -> {
            if (productRepository.count() == 0) {
                CategoryModel electronics = categoryRepository.findByName("Electronics").orElseThrow();
                CategoryModel books = categoryRepository.findByName("Books").orElseThrow();
                CategoryModel clothing = categoryRepository.findByName("Clothing").orElseThrow();

                ProductModel laptop = createProduct("Laptop", "High performance laptop", new BigDecimal("1200.00"), 10, electronics);
                productRepository.save(laptop);

                ProductModel keyboard = createProduct("Mechanical keyboard", "RGB mechanical keyboard", new BigDecimal("150.00"), 25, electronics);
                productRepository.save(keyboard);

                ProductModel book = createProduct("Clean code", "A handbook of agile software craftsmanship", new BigDecimal("45.00"), 50, books);
                productRepository.save(book);

                ProductModel shirt = createProduct("Basic T-Shirt", "Cotton t-shirt", new BigDecimal("20.00"), 100, clothing);
                productRepository.save(shirt);
            }
        };
    }

    private CategoryModel createCategory(String name, String description) {
        CategoryModel newCategory = new CategoryModel();
        newCategory.setName(name);
        newCategory.setDescription(description);
        newCategory.setState(CategoryState.ACTIVE);

        return newCategory;
    }

    private ProductModel createProduct(String name, String description, BigDecimal price, int stock, CategoryModel category) {
        ProductModel newProduct = new ProductModel();
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setPrice(price);
        newProduct.setStock(stock);
        newProduct.setCategory(category);
        newProduct.setState(ProductState.ACTIVE);
        newProduct.setCreatedAt(LocalDateTime.now());

        return newProduct;
    }
}
