package br.eduardobraz.sprint4.exceptions;

import br.eduardobraz.sprint4.dto.PartidoFormDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseErros {

    private String message;
    private HttpStatus status;
    private List<String> errors;

    public ResponseErros(String message, HttpStatus status, String error) {
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

}
