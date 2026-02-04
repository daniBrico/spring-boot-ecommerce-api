package ecommerce_java_springboot.controllers.auth;

import ecommerce_java_springboot.dto.AuthResponse;
import ecommerce_java_springboot.dto.LoginRequest;
import ecommerce_java_springboot.dto.RegisterRequest;
import ecommerce_java_springboot.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(
    @RequestBody LoginRequest request
  ) {
    return ResponseEntity.ok(authService.login(request));
  }

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(
    @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(authService.register(request));
  }
}
