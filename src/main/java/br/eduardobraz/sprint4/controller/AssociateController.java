package br.eduardobraz.sprint4.controller;

import br.eduardobraz.sprint4.dto.*;
import br.eduardobraz.sprint4.repository.AssociatePartidoRepository;
import br.eduardobraz.sprint4.repository.AssociateRepository;
import br.eduardobraz.sprint4.repository.PartidoRepository;
import br.eduardobraz.sprint4.services.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssociateController {
    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private AssociatePartidoRepository associatePartidoRepository;

    @Autowired
    private AssociateService associateService;

    @PostMapping("/associados")
    @Transactional
    public ResponseEntity<AssociateDTO> saveAssociate(@RequestBody @Validated AssociateFormDTO body){
        AssociateDTO associate = this.associateService.saveAssociate(body);
        return ResponseEntity.ok(associate);
    }

    @PostMapping("/associados/partidos")
    @Transactional
    public ResponseEntity<AssociatePartidoDTO> saveAssociateToPartido(@RequestBody AssociatePartidoFormDTO body){
        AssociatePartidoDTO associate = this.associateService.saveAssociateToPartido(body);
        return ResponseEntity.ok(associate);
    }

    @Transactional
    @GetMapping("/associados")
    public ResponseEntity<List<AssociateDTO>> getAssociates(@RequestParam(required = false) String cargo,
                                                            @RequestParam(required = false) String sort){
        List<AssociateDTO> associates = this.associateService.getAssociates(cargo, sort);
        return ResponseEntity.ok(associates);
    }

    @Transactional
    @GetMapping("/associados/{id}")
    public ResponseEntity<AssociateDTO> getAssociate(@PathVariable Long id){
        AssociateDTO associate = this.associateService.getAssociate(id);
        return ResponseEntity.ok(associate);
    }

    @PutMapping("/associados/{id}")
    @Transactional
    public ResponseEntity<AssociateDTO> updateAssociate(@PathVariable Long id, @RequestBody AssociateFormDTO body){
        AssociateDTO associate = this.associateService.updateAssociate(id, body);
        return ResponseEntity.ok(associate);
    }

    @Transactional
    @DeleteMapping("/associados/{id}")
    public ResponseEntity<AssociateDTO> removeAssociate(@PathVariable Long id){
        this.associateService.removeAssociate(id);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping("/associados/{idAssociate}/partidos/{idPartido}")
    public ResponseEntity<?> removeAssociateFromPartido(@PathVariable(required = true) Long idAssociate,
                                                                   @PathVariable(required = true) Long idPartido){
        this.associateService.removeAssociateFromPartido(idAssociate, idPartido);
        return ResponseEntity.ok().build();
    }

}
