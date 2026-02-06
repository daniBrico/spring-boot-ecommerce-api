package ecommerce_java_springboot.dto.request;

public record LoginRequest(
  String email,
  String password
) {
}
