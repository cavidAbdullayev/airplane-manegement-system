package org.example.airplanemanegementsystem.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class HandleExceptions {
    @ExceptionHandler(GeneralException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBusinessExceptions(GeneralException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInputValidation() {
        return ErrorResponse.builder()
                .message("Input error")
                .build();
    }
}