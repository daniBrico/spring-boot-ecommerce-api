package ecommerce_java_springboot.controllers.auth;

import ecommerce_java_springboot.dto.request.LoginRequest;
import ecommerce_java_springboot.dto.request.RegisterRequest;
import ecommerce_java_springboot.dto.response.AuthResponse;
import ecommerce_java_springboot.dto.response.UserResponse;
import ecommerce_java_springboot.models.UserModel;
import ecommerce_java_springboot.security.JwtCookieManager;
import ecommerce_java_springboot.services.AuthService;
import ecommerce_java_springboot.services.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtCookieManager jwtCookieManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        UserModel user = authService.login(request);

        String token = jwtService.generateToken(user);
        jwtCookieManager.addJwtCookie(response, token);

        AuthResponse authResponse = new AuthResponse(new UserResponse(user.getId(), user.getName(), user.getEmail()));

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request, HttpServletResponse response) {
        UserModel user = authService.register(request);

        String token = jwtService.generateToken(user);
        jwtCookieManager.addJwtCookie(response, token);

        AuthResponse authResponse = new AuthResponse(new UserResponse(user.getId(), user.getName(), user.getEmail()));

        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        jwtCookieManager.clearJwtCookie(response);

        return ResponseEntity.noContent().build();
    }
}
