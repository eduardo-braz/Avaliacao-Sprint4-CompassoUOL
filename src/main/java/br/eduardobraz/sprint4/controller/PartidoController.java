package br.eduardobraz.sprint4.controller;

import br.eduardobraz.sprint4.dto.AssociateDTO;
import br.eduardobraz.sprint4.dto.PartidoDTO;
import br.eduardobraz.sprint4.dto.PartidoFormDTO;
import br.eduardobraz.sprint4.repository.AssociatePartidoRepository;
import br.eduardobraz.sprint4.repository.AssociateRepository;
import br.eduardobraz.sprint4.repository.PartidoRepository;
import br.eduardobraz.sprint4.services.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PartidoController {

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private AssociatePartidoRepository associatePartidoRepository;

    @Autowired
    private PartidoService partidoService;

    @Transactional
    @GetMapping("/partidos")
    public ResponseEntity<List<PartidoDTO>> getPartidos(@RequestParam(required = false) String ideology){
        List<PartidoDTO> partidos = this.partidoService.getPartidos(ideology);
        return ResponseEntity.ok(partidos);
    }

    @Transactional
    @GetMapping("/partidos/{id}")
    public ResponseEntity<PartidoDTO> getPartido(@PathVariable Long id){
        PartidoDTO partido = this.partidoService.getPartido(id);
        return ResponseEntity.ok(partido);
    }

    @PostMapping("/partidos")
    @Transactional
    public ResponseEntity<PartidoDTO> savePartido(@RequestBody PartidoFormDTO body){
        PartidoDTO partido = this.partidoService.savePartido(body);
        return ResponseEntity.ok(partido);
    }

    @PutMapping("/partidos/{id}")
    @Transactional
    public ResponseEntity<PartidoDTO> updatePartido(@PathVariable Long id, @RequestBody PartidoFormDTO body){
        PartidoDTO partido = this.partidoService.updatePartido(id, body);
        return ResponseEntity.ok(partido);
    }

    @Transactional
    @DeleteMapping("/partidos/{id}")
    public ResponseEntity<PartidoDTO> removePartido(@PathVariable Long id){
       this.partidoService.removePartido(id);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @GetMapping("/partidos/{id}/associados")
    public ResponseEntity<List<AssociateDTO>> listAssociatesPartido(@PathVariable(required = true) Long id){
        List<AssociateDTO> associates = this.partidoService.listAssociatesPartido(id);
        return ResponseEntity.ok(associates);
    }

}
