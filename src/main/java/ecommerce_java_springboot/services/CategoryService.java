package ecommerce_java_springboot.services;

import ecommerce_java_springboot.common.exception.ResourceNotFoundException;
import ecommerce_java_springboot.dto.category.CategoryDTO;
import ecommerce_java_springboot.mappers.CategoryMapper;
import ecommerce_java_springboot.models.CategoryModel;
import ecommerce_java_springboot.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getCategories() {
        List<CategoryModel> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("No categories found");
        }

        return categories.stream().map(CategoryMapper::toCategoryDTO).toList();
    }
}
