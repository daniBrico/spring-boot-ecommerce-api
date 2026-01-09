package ecommerce_java_springboot.services;

import ecommerce_java_springboot.models.ProductModel;
import ecommerce_java_springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
  @Autowired
  ProductRepository productRepository;

  public ArrayList<ProductModel> getProducts() {
    return (ArrayList<ProductModel>) productRepository.findAll();
  }

  public ProductModel setProduct(ProductModel product) {
    return productRepository.save(product);
  }
}
