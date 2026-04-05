package ecommerce_java_springboot.controllers;

import ecommerce_java_springboot.dto.order.OrderResponse;
import ecommerce_java_springboot.mappers.order.OrderMapper;
import ecommerce_java_springboot.models.UserModel;
import ecommerce_java_springboot.models.order.OrderModel;
import ecommerce_java_springboot.services.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public ResponseEntity<OrderResponse> checkout(@AuthenticationPrincipal UserModel user) {
        OrderModel order = orderService.checkout(user);

        return ResponseEntity.ok(orderMapper.toResponse(order));
    }
}
