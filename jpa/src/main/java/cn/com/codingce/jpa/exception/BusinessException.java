package cn.com.codingce.jpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BusinessException extends RuntimeException {

    private HttpStatus status;

    public BusinessException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public ResponseEntity<String> getDefaultRestResponseEntity() {
        return new ResponseEntity<>(this.getMessage(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> getRestResponseEntity() {
        return new ResponseEntity<>(this.getMessage(), status);
    }

    public BusinessException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
