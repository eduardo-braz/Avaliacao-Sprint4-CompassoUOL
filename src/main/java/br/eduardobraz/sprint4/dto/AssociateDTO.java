package br.eduardobraz.sprint4.dto;

import br.eduardobraz.sprint4.entity.CargoPoliticoEnum;
import br.eduardobraz.sprint4.entity.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AssociateDTO {
    private Long id;
    private String name;
    private CargoPoliticoEnum cargo;
    private GenderEnum gender;      // Sexo
    private Date birthDate;         // Data de nascimento

}
