package br.eduardobraz.sprint4.dto;

import br.eduardobraz.sprint4.entity.IdeologyEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
@Data
public class PartidoFormDTO {

    @NotNull(message = "Nome não pode ser nulo")
    @NotBlank(message = "Nome não pode ser vazio")
    private String name;
    @NotNull(message = "Sigla não pode ser nula")
    @NotBlank(message = "Sigla não pode ser nula")
    private String initials;
    @NotNull(message = "Ideologia não pode ser nula")
    @Enumerated(EnumType.STRING)
    private IdeologyEnum ideology;
    @NotNull(message = "Data não pode ser nula")
    @PastOrPresent
    @Temporal(TemporalType.DATE)
    private Date foundationDate;
}
