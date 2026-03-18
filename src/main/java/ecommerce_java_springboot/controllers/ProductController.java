package ecommerce_java_springboot.controllers;

import ecommerce_java_springboot.dto.product.CreateProductRequest;
import ecommerce_java_springboot.dto.product.ProductDTO;
import ecommerce_java_springboot.repositories.ProductRepository;
import ecommerce_java_springboot.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> products = productService.getProducts();

        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody CreateProductRequest request) {
        ProductDTO product = productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
