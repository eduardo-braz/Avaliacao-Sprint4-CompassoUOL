package br.eduardobraz.sprint4.dto;

import br.eduardobraz.sprint4.entity.Associate;
import br.eduardobraz.sprint4.entity.Partido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociatePartidoDTO {

    private Long id;
    private Associate associate;
    private Partido partido;

}
