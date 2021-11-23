package by.it.employeerestservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(EmployeeServiceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public EmployeeServiceExceptionMessage handleNotFoundException(EmployeeServiceNotFoundException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return this.generateExceptionMessage(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EmployeeServiceExceptionMessage handleAgeValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        String message = Objects.requireNonNull(ex.getFieldError()).getDefaultMessage();
        log.warn(message, ex);
        return this.generateExceptionMessage(HttpStatus.BAD_REQUEST, message, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EmployeeServiceExceptionMessage handleInvalidRequestParam(MethodArgumentTypeMismatchException ex, WebRequest request) {
        log.error("Invalid request param", ex);
        return this.generateExceptionMessage(HttpStatus.BAD_REQUEST, "Invalid request param", request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EmployeeServiceExceptionMessage handleNegativeIdParam(ConstraintViolationException ex, WebRequest request) {
        log.error("id must be equal or greater than 1", ex);
        return this.generateExceptionMessage(HttpStatus.BAD_REQUEST, "id must be equal or greater than 1", request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public EmployeeServiceExceptionMessage handleAllUncaughtException(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return this.generateExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
    }

    private EmployeeServiceExceptionMessage generateExceptionMessage(HttpStatus status, String message, WebRequest request) {
        return EmployeeServiceExceptionMessage
                .builder()
                .status(status.value())
                .timestamp(new Date())
                .message(message)
                .path(request.getDescription(false))
                .build();
    }
}
