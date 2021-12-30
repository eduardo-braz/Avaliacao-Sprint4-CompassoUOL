package br.eduardobraz.sprint4.services;

import br.eduardobraz.sprint4.dto.AssociateDTO;
import br.eduardobraz.sprint4.dto.AssociatePartidoDTO;
import br.eduardobraz.sprint4.dto.PartidoDTO;
import br.eduardobraz.sprint4.dto.PartidoFormDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PartidoService {

    PartidoDTO savePartido(PartidoFormDTO body);

    List<PartidoDTO> getPartidos(String ideology);

    PartidoDTO getPartido(Long id);

    PartidoDTO updatePartido(Long id, PartidoFormDTO body);

    ResponseEntity<PartidoDTO> removePartido(Long id);

    List<AssociateDTO> listAssociatesPartido(Long id);


}
