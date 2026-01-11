package ecommerce_java_springboot.controllers.auth;

import ecommerce_java_springboot.dto.RegisterRequest;
import ecommerce_java_springboot.dto.TokenResponse;
import ecommerce_java_springboot.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<TokenResponse> register(@RequestBody final RegisterRequest request) {
    final TokenResponse token = authService.register(request);
    return ResponseEntity.ok(token);
  }
}
