package ecommerce_java_springboot.dto.request;

// Cualquiero otro tipo de dato que se necesite para crear el usuario
public record RegisterRequest(
  String email,
  String password,
  String name
) {
}
