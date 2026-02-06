package ecommerce_java_springboot.dto.response;

public record UserResponse(
  Long id,
  String name,
  String email
) {
}
