package ecommerce_java_springboot.services;

import ecommerce_java_springboot.dto.AuthResponse;
import ecommerce_java_springboot.dto.LoginRequest;
import ecommerce_java_springboot.dto.RegisterRequest;
import ecommerce_java_springboot.models.UserModel;
import ecommerce_java_springboot.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthResponse login(LoginRequest request) {
    UserModel user = userRepository
      .findByEmail(request.email())
      .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(request.password(), user.getPassword())) {
      throw new RuntimeException("Invalid credentials");
    }

    String token = jwtService.generateToken(user);

    return new AuthResponse(token);
  }

  public AuthResponse register(RegisterRequest request) {
    if (userRepository.findByEmail(request.email()).isPresent()) {
      throw new RuntimeException("Email already exists");
    }

    UserModel user = UserModel.builder()
      .email(request.email())
      .name(request.name())
      .password(passwordEncoder.encode(request.password()))
      .build();

    userRepository.save(user);

    String token = jwtService.generateToken(user);

    return new AuthResponse(token);
  }
}
