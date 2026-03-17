package ecommerce_java_springboot.services;

import ecommerce_java_springboot.common.exception.ResourceNotFoundException;
import ecommerce_java_springboot.dto.product.CreateProductRequest;
import ecommerce_java_springboot.dto.product.ProductDTO;
import ecommerce_java_springboot.mappers.ProductMapper;
import ecommerce_java_springboot.models.CategoryModel;
import ecommerce_java_springboot.models.ProductModel;
import ecommerce_java_springboot.models.enums.ProductState;
import ecommerce_java_springboot.repositories.CategoryRepository;
import ecommerce_java_springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getProducts() {
        List<ProductModel> products = productRepository.findAll();

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products found");
        }

        return products.stream().map(ProductMapper::mapToDTO).toList();
    }

    public ProductDTO createProduct(CreateProductRequest request) {

        CategoryModel category = categoryRepository.findById(request.categoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        ProductModel product = new ProductModel();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setState(ProductState.ACTIVE);
        product.setCategory(category);

        ProductModel savedProduct = productRepository.save(product);

        return ProductMapper.mapToDTO(savedProduct);
    }
}
