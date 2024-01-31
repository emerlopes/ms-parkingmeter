package com.techchallenge.msparkingmeter.application.exceptions;

import feign.FeignException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ParkingControlValidationException.class)
    @ResponseBody
    public ResponseEntity<String> handleParkingControlValidationException(ParkingControlValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ParkingControlNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleParkingControlNotFoundException(ParkingControlNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(FeignException.NotFound.class)
    @ResponseBody
    public ResponseEntity<String> handleFeignStatusException(FeignException.NotFound e, HttpServletResponse response) {
        return ResponseEntity
                .status(e.status())
                .body("Erro ao buscar opção de pagamento: " + e.getMessage());
    }

}
