package ecommerce_java_springboot.common;

public record ApiResponse<T>(boolean success, T data) {
}
