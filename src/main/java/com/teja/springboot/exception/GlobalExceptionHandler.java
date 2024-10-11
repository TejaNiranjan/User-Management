package com.teja.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> ResourceNotFoundExceptionHandling(ResourceNotFoundException Ex,
                                                                           WebRequest webRequest){
        ErrorResponse errorResponse=new ErrorResponse(
                LocalDateTime.now(),
                Ex.getMessage(),
                webRequest.getDescription(false),
                "User_Not_Found"
        );
        return  new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> EmailAlreadyExistsExceptionHandling(EmailAlreadyExistsException Ex,
                                                                           WebRequest webRequest){
        ErrorResponse errorResponse=new ErrorResponse(
                LocalDateTime.now(),
                Ex.getMessage(),
                webRequest.getDescription(false),
                "Email_Already_Exists"
        );
        return  new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> GlobalExceptionHandling(Exception Ex,
                                                                             WebRequest webRequest){
        ErrorResponse errorResponse=new ErrorResponse(
                LocalDateTime.now(),
                Ex.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );
        return  new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
