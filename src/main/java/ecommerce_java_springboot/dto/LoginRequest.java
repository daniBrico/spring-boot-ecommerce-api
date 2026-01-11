package ecommerce_java_springboot.dto;

public record LoginRequest(
  String email,
  String password
) {
}
