package br.eduardobraz.sprint4.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestControllerAdvice
public class ResponseExceptions extends ResponseEntityExceptionHandler {
    private String message;

    @ExceptionHandler(value = {ResourceNotFoundException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<?> notFound(RuntimeException ex) {
        String bodyOfResponse = ex.getMessage();
        return new ResponseEntity(bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<?> handleInvalidArgument(RuntimeException ex) {
        String bodyOfResponse = ex.getMessage();
        return new ResponseEntity(bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

}
