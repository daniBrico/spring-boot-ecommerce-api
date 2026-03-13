package ecommerce_java_springboot.controllers;

import ecommerce_java_springboot.dto.ProductDTO;
import ecommerce_java_springboot.models.ProductModel;
import ecommerce_java_springboot.repositories.ProductRepository;
import ecommerce_java_springboot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> products = productService.getProducts();

        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ProductModel setProduct(@RequestBody ProductModel product) {
        return productRepository.save(product);
    }
}
