package ecommerce_java_springboot.common.exception;

import ecommerce_java_springboot.common.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleUserNotFound() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ApiErrorResponse(false, "User not found", "USER_NOT_FOUND"));
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ApiErrorResponse> handleInvalidCredentials() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new ApiErrorResponse(false, "Invalid credentials", "INVALID_CREDENTIALS"));
  }

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<ApiErrorResponse> handleEmailExists() {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new ApiErrorResponse(false, "Email already exists", "EMAIL_EXISTS"));
  }
}
