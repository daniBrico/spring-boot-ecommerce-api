package ecommerce_java_springboot.controllers;

import ecommerce_java_springboot.dto.cart.CartResponse;
import ecommerce_java_springboot.dto.request.AddCartItemRequest;
import ecommerce_java_springboot.services.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponse> addItem(@Valid @RequestBody AddCartItemRequest request){
        CartResponse cart = cartService.addItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }
}
