package ecommerce_java_springboot.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiError {

    private int status;
    private String error;
    private String message;
    private String path;
}
