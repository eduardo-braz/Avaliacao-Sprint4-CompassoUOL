package br.eduardobraz.sprint4.services;

import br.eduardobraz.sprint4.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssociateService {


    AssociateDTO saveAssociate(AssociateFormDTO body);

    AssociateDTO getAssociate(Long id);

    List<AssociateDTO> getAssociates(String cargo, String order);

    AssociateDTO updateAssociate(Long id, AssociateFormDTO body);

    ResponseEntity<AssociateDTO> removeAssociate(Long id);
    
    ResponseEntity<?> removeAssociateFromPartido(Long idAssociate, Long idPartido);

    AssociatePartidoDTO saveAssociateToPartido(AssociatePartidoFormDTO body);

}
