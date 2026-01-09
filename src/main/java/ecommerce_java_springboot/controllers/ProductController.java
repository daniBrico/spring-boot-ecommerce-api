package ecommerce_java_springboot.controllers;

import ecommerce_java_springboot.models.ProductModel;
import ecommerce_java_springboot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  ProductService productService;

  @GetMapping
  public ArrayList<ProductModel> getProducts() {
    return productService.getProducts();
  }
}
