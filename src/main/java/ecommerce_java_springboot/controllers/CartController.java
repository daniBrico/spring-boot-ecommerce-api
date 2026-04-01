package ecommerce_java_springboot.controllers;

import ecommerce_java_springboot.dto.cart.CartResponse;
import ecommerce_java_springboot.dto.request.AddCartItemRequest;
import ecommerce_java_springboot.services.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<CartResponse> addItem(@Valid @RequestBody AddCartItemRequest request){
        CartResponse cart = cartService.addItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping
    public ResponseEntity<CartResponse> getCart(){
        CartResponse cart = cartService.getCart();
        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }
}
