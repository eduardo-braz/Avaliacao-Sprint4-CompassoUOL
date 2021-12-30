package br.eduardobraz.sprint4.services;

import br.eduardobraz.sprint4.dto.AssociateDTO;
import br.eduardobraz.sprint4.dto.PartidoDTO;
import br.eduardobraz.sprint4.dto.PartidoFormDTO;
import br.eduardobraz.sprint4.entity.AssociatePartido;
import br.eduardobraz.sprint4.entity.IdeologyEnum;
import br.eduardobraz.sprint4.entity.Partido;
import br.eduardobraz.sprint4.repository.AssociatePartidoRepository;
import br.eduardobraz.sprint4.repository.AssociateRepository;
import br.eduardobraz.sprint4.repository.PartidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartidoServiceImpl implements PartidoService{

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private AssociatePartidoRepository associatePartidoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PartidoDTO savePartido(PartidoFormDTO body) {
        try {
            Partido partido = modelMapper.map(body, Partido.class);
            Partido partidoSave = this.partidoRepository.save(partido);
            return modelMapper.map(partidoSave, PartidoDTO.class);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Argumento inválido.");
        }
    }

    @Override
    public List<PartidoDTO> getPartidos(String ideology) {
        if (ideology != null) {
            IdeologyEnum ideologyEnum = modelMapper.map(ideology, IdeologyEnum.class);
            Optional<List<Partido>> partidos = partidoRepository.findByIdeologyEquals(ideologyEnum);
            if (partidos.isPresent()) {
                return partidos.get().stream().map(pt ->
                        modelMapper.map(pt, PartidoDTO.class)).collect(Collectors.toList());
            }
            throw new ResourceNotFoundException("Não foram encontrados partidos com esta ideologia.");
        }
        else {
            List<Partido> partidos = partidoRepository.findAll();
            return partidos.stream().map(pt ->
                    modelMapper.map(pt, PartidoDTO.class)).collect(Collectors.toList());
        }
    }

    @Override
    public PartidoDTO getPartido(Long id){
        Optional<Partido> partido = partidoRepository.findById(id);
        if (partido.isPresent()) {
            return modelMapper.map(partido.get(), PartidoDTO.class);
        }
        throw new ResourceNotFoundException("Nenhum partido encontrado com id " + id);
    }

    @Override
    public PartidoDTO updatePartido(Long id, PartidoFormDTO body){
        Optional<Partido> partidoOpt = partidoRepository.findById(id);
        if (partidoOpt.isPresent()){
            Partido partido = partidoOpt.get();
            partido.setIdeology(body.getIdeology());
            partido.setFoundationDate(body.getFoundationDate());
            partido.setName(body.getName());
            partido.setInitials(body.getInitials());
            return modelMapper.map(partido, PartidoDTO.class);
        }
        throw new RuntimeException();  // Alterar exceção
    }

    @Override
    public ResponseEntity<PartidoDTO> removePartido(Long id){
        Optional<Partido> partido = partidoRepository.findById(id);
        if (partido.isPresent()){
            partidoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new RuntimeException();   // Trocar exceção
    }

    @Override
    public  List<AssociateDTO> listAssociatesPartido(Long id) {
        Optional<List<AssociatePartido>> assPartido = this.associatePartidoRepository.findByPartidoId(id);
        if (assPartido.isPresent()){
            List<AssociatePartido> associatesPartido = assPartido.get();
            return associatesPartido.stream().map(as ->
                    modelMapper.map(as.getAssociate(), AssociateDTO.class)).collect(Collectors.toList());
        }
        throw new ResourceNotFoundException("Não há associados neste partido.");
    }
}
