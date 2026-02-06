package ecommerce_java_springboot.common.exception;

public class EmailAlreadyExistsException extends RuntimeException{
  public EmailAlreadyExistsException() {
        super("Email already exists");
    }
}
