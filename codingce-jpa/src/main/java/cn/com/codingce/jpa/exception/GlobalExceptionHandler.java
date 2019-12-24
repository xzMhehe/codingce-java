package cn.com.codingce.jpa.exception;

import cn.com.codingce.jpa.common.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity validateExceptionHandler(ValidationException exception) {
        logger.error("method argument not valid", exception);
        ConstraintViolationException exs = (ConstraintViolationException) exception;
        Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
        String errorMessage = violations.iterator().next().getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<String> exception(Exception e) {
        logger.error("internal server error", e);
        return new ResponseEntity(Translator.toLocale("server.internal.error.message"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("bean validation error", e);
        BindingResult bindingResult = e.getBindingResult();
        String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
        return new ResponseEntity(defaultMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<String> businessException(BusinessException e) {
        logger.error("business exception", e);
        if (e.getStatus() == null) {
            return e.getDefaultRestResponseEntity();
        }
        return e.getRestResponseEntity();
    }
}
