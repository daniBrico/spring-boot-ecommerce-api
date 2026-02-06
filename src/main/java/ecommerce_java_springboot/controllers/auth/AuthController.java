package ecommerce_java_springboot.controllers.auth;

import ecommerce_java_springboot.common.ApiResponse;
import ecommerce_java_springboot.dto.request.LoginRequest;
import ecommerce_java_springboot.dto.request.RegisterRequest;
import ecommerce_java_springboot.dto.response.LoginResponse;
import ecommerce_java_springboot.dto.response.RegisterResponse;
import ecommerce_java_springboot.dto.response.UserResponse;
import ecommerce_java_springboot.models.UserModel;
import ecommerce_java_springboot.security.JwtCookieManager;
import ecommerce_java_springboot.services.AuthService;
import ecommerce_java_springboot.services.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;
  private final JwtCookieManager jwtCookieManager;
  private final JwtService jwtService;

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<LoginResponse>> login(
      @RequestBody LoginRequest request, HttpServletResponse response) {
    UserModel user = authService.login(request);

    String token = jwtService.generateToken(user);
    jwtCookieManager.addJwtCookie(response, token);

    LoginResponse loginResponse =
        new LoginResponse(new UserResponse(user.getId(), user.getName(), user.getEmail()));

    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, loginResponse));
  }

  @PostMapping("/register")
  public ResponseEntity<ApiResponse<RegisterResponse>> register(
      @RequestBody RegisterRequest request, HttpServletResponse response) {
    UserModel user = authService.register(request);

    String token = jwtService.generateToken(user);
    jwtCookieManager.addJwtCookie(response, token);

    RegisterResponse registerResponse =
        new RegisterResponse(new UserResponse(user.getId(), user.getName(), user.getEmail()));

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ApiResponse<>(true, registerResponse));
  }
}
