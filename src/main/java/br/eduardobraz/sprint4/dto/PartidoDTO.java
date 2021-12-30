package br.eduardobraz.sprint4.dto;

import br.eduardobraz.sprint4.entity.IdeologyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PartidoDTO {

    private Long id;
    private String name;
    private String initials;
    private IdeologyEnum ideology;
    private Date foundationDate;
}
