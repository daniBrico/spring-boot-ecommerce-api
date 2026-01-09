package ecommerce_java_springboot.controllers;

import ecommerce_java_springboot.models.ProductModel;
import ecommerce_java_springboot.repositories.ProductRepository;
import ecommerce_java_springboot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  ProductService productService;
  @Autowired
  private ProductRepository productRepository;

  @GetMapping
  public ArrayList<ProductModel> getProducts() {
    return productService.getProducts();
  }

  @PostMapping
  public ProductModel setProduct(@RequestBody ProductModel product) {
    return productRepository.save(product);
  }
}
