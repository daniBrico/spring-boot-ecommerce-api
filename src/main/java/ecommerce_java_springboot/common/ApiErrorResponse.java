package ecommerce_java_springboot.common;

public record ApiErrorResponse(boolean success, String message, String errorCode) {}
