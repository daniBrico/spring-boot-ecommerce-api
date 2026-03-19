package ecommerce_java_springboot.services;

import ecommerce_java_springboot.common.exception.ResourceNotFoundException;
import ecommerce_java_springboot.dto.product.CreateProductRequest;
import ecommerce_java_springboot.dto.product.ProductDTO;
import ecommerce_java_springboot.models.CategoryModel;
import ecommerce_java_springboot.models.ProductModel;
import ecommerce_java_springboot.models.enums.ProductState;
import ecommerce_java_springboot.repositories.CategoryRepository;
import ecommerce_java_springboot.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    void shouldCreateProductWhenCategoryExists() {

        CreateProductRequest request = new CreateProductRequest(
                "Laptop",
                "High performance laptop",
                BigDecimal.valueOf(1500),
                10,
                1L
        );

        CategoryModel category = new CategoryModel();
        category.setId(1L);

        ProductModel savedProduct = new ProductModel();
        savedProduct.setId(1L);
        savedProduct.setName(request.name());
        savedProduct.setDescription(request.description());
        savedProduct.setPrice(request.price());
        savedProduct.setStock(request.stock());
        savedProduct.setState(ProductState.ACTIVE);
        savedProduct.setCategory(category);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.save(any(ProductModel.class))).thenReturn(savedProduct);

        ProductDTO result = productService.createProduct(request);
        assertEquals("Laptop", result.name());

        verify(categoryRepository).findById(1L);
        verify(productRepository).save(any(ProductModel.class));
    }

    @Test
    void shouldThrowExceptionWhenCategoryIsNotFound() {

        CreateProductRequest request = new CreateProductRequest(
                "Laptop",
                "High performance laptop",
                BigDecimal.valueOf(1500),
                10,
                1L
        );

        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.createProduct(request));

        verify(categoryRepository).findById(1L);
        verify(productRepository, never()).save(any());
    }
}
