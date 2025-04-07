package org.db1.api.infra.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {
    private static final Logger log = LogManager.getLogger(ErrorHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrors>> handleError400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        for (FieldError e : errors) {
            log.error("Campo inválido: {}, Mensagem: {}", e.getField(), e.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrors::new).toList());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleEnumError(IllegalArgumentException exception) {
        log.error("Campo inválido: {}", exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    public record ValidationErrors(String field, String message) {
        public ValidationErrors(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
