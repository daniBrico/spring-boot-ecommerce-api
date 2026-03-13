package ecommerce_java_springboot.services;

import ecommerce_java_springboot.common.exception.ResourceNotFoundException;
import ecommerce_java_springboot.dto.ProductDTO;
import ecommerce_java_springboot.mappers.ProductMapper;
import ecommerce_java_springboot.models.ProductModel;
import ecommerce_java_springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductDTO> getProducts() {
        List<ProductModel> products = productRepository.findAll();

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products found");
        }

        return products.stream().map(ProductMapper::toDto).toList();
    }

    public ProductModel setProduct(ProductModel product) {
        return productRepository.save(product);
    }
}
