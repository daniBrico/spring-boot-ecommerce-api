package ecommerce_java_springboot.config;

import ecommerce_java_springboot.models.CategoryModel;
import ecommerce_java_springboot.models.enums.CategoryState;
import ecommerce_java_springboot.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    private CategoryModel createCategory(String name, String description) {
        CategoryModel newCategory = new CategoryModel();
        newCategory.setName(name);
        newCategory.setDescription(description);
        newCategory.setState(CategoryState.ACTIVE);

        return newCategory;
    }
}
