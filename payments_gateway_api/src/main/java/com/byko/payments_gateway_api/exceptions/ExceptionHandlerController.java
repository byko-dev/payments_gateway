package com.byko.payments_gateway_api.exceptions;;


import com.byko.payments_gateway_api.pojos.Status;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Status handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        exception.getBindingResult().getFieldErrors().forEach(e -> {
            log.error(String.format("Request path => %s, validation request failed on => %s, POJO => %s, message => %s",
                    request.getServletPath(), e.getField(), e.getObjectName(), e.getDefaultMessage()));
        });
        return new Status(exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage(), request.getServletPath());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Status handle(ConstraintViolationException exception, HttpServletRequest request) {
        return new Status(exception.getMessage(), request.getServletPath());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Status resourceNotFoundExceptionHandler(ResourceNotFoundException exception, HttpServletRequest request){
        log.error(String.format("ResourceNotFoundException => %s, request path => %s", exception.getMessage(), request.getServletPath()));
        return new Status(exception.getMessage(), request.getServletPath());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Status badRequestExceptionHandler(BadRequestException exception, HttpServletRequest request){
        log.error(String.format("BadRequest => %s, request path => %s",
                exception.getMessage(), request.getServletPath()));
        return new Status(exception.getMessage(), request.getServletPath());
    }
}
