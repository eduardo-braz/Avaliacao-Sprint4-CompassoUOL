package br.eduardobraz.sprint4.dto;

import br.eduardobraz.sprint4.entity.CargoPoliticoEnum;
import br.eduardobraz.sprint4.entity.GenderEnum;
import lombok.Data;

import java.util.Date;

@Data
public class AssociateFormDTO {
    private String name;
    private CargoPoliticoEnum cargo;
    private GenderEnum gender;      // Sexo
    private Date birthDate;         // Data de nascimento
}
