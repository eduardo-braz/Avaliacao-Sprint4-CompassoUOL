package br.eduardobraz.sprint4.services;

import br.eduardobraz.sprint4.dto.*;
import br.eduardobraz.sprint4.entity.Associate;
import br.eduardobraz.sprint4.entity.AssociatePartido;
import br.eduardobraz.sprint4.entity.CargoPoliticoEnum;
import br.eduardobraz.sprint4.entity.Partido;
import br.eduardobraz.sprint4.repository.AssociatePartidoRepository;
import br.eduardobraz.sprint4.repository.AssociateRepository;
import br.eduardobraz.sprint4.repository.PartidoRepository;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssociateServiceImpl implements  AssociateService{

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private AssociatePartidoRepository associatePartidoRepository;

    @Override
    public AssociateDTO saveAssociate(AssociateFormDTO body) {
        // Converter formato data
        try {
            Associate associate = modelMapper.map(body, Associate.class);
            Associate associateSave = this.associateRepository.save(associate);
            return modelMapper.map(associateSave, AssociateDTO.class);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public AssociateDTO getAssociate(Long id) {
        Optional<Associate> associate = associateRepository.findById(id);
        if (associate.isPresent()) {
            return modelMapper.map(associate.get(), AssociateDTO.class);
        }
        throw new ResourceNotFoundException("Nenhum associado encontrado com id " + id);
    }

    @Override
    public List<AssociateDTO> getAssociates(String cargo, String sort) {
        if (cargo != null) {
            CargoPoliticoEnum cargoPoliticoEnum = modelMapper.map(cargo, CargoPoliticoEnum.class);
            if (sort == null || sort.compareToIgnoreCase("ASC")==0) {
                Optional<List<Associate>> associates = associateRepository.findByCargoOrderByNameAsc(cargoPoliticoEnum);
                if (associates.isPresent()) {
                    return associates.get().stream().map(as ->
                            modelMapper.map(as, AssociateDTO.class)).collect(Collectors.toList());
                }
                throw new ResourceNotFoundException("Não foram encontrados associados com este cargo.");
            } else {
                if (sort.compareToIgnoreCase("DESC")==0){
                    Optional<List<Associate>> associates = associateRepository.findByCargoOrderByNameDesc(cargoPoliticoEnum);
                    if (associates.isPresent()) {
                        return associates.get().stream().map(as ->
                                modelMapper.map(as, AssociateDTO.class)).collect(Collectors.toList());
                    }
                    throw new ResourceNotFoundException("Não foram encontrados associados com este cargo.");
                }
            }
        } else {
            if (sort.compareToIgnoreCase("DESC") == 0) {
                try {
                    List<Associate> associates = associateRepository.findAll(Sort.by("Name").descending());
                    return associates.stream().map(as ->
                            modelMapper.map(as, AssociateDTO.class)).collect(Collectors.toList());
                }catch (Exception e) {
                    throw new ResourceNotFoundException("Não foram encontrados associados.");
                }
            }
        }
        List<Associate> associates = associateRepository.findAll();
        try {
            return associates.stream().map(as ->
                    modelMapper.map(as, AssociateDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foram encontrados associados.");
        }
    }

    @Override
    public AssociateDTO updateAssociate(Long id, AssociateFormDTO body) {
        Optional<Associate> associateOpt = associateRepository.findById(id);
        if (associateOpt.isPresent()){
            Associate associate = associateOpt.get();
            associate.setName(body.getName());
            associate.setCargo(body.getCargo());
            associate.setBirthDate(body.getBirthDate());
            associate.setGender(body.getGender());
            return modelMapper.map(associate, AssociateDTO.class);
        }
        throw new RuntimeException();  // Alterar exceção
    }

    @Override
    public ResponseEntity<AssociateDTO> removeAssociate(Long id) {
        Optional<Associate> associate = associateRepository.findById(id);
        if (associate.isPresent()){
            associateRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new RuntimeException();  // Trocar exceção
    }

    @Override
    public ResponseEntity<?> removeAssociateFromPartido(Long idAssociate, Long idPartido) {
        Optional<AssociatePartido> assPartido =
                this.associatePartidoRepository.findByAssociateIdAndPartidoId(idAssociate, idPartido);
        if (assPartido.isPresent()){
            associateRepository.deleteById(assPartido.get().getId());
            return ResponseEntity.ok().build();
        }
        throw new ResourceNotFoundException("Associação entre associado e partido não encontrado.");
    }

    @Override
    public AssociatePartidoDTO saveAssociateToPartido(AssociatePartidoFormDTO body) {
        Optional<Associate> associate = this.associateRepository.findById(body.getIdAssociado());
        if (associate.isPresent()){
            Optional<Partido> partido = this.partidoRepository.findById(body.getIdPartido());
            if (partido.isPresent()){
                AssociatePartido asPartido = new AssociatePartido();
                asPartido.setPartido(partido.get());
                asPartido.setAssociate(associate.get());
                AssociatePartido associateSave = this.associatePartidoRepository.save(asPartido);
                return modelMapper.map(associateSave, AssociatePartidoDTO.class);
            }
            throw new ResourceNotFoundException("Nenhum partido encontrado com id " + body.getIdPartido());
        }
        throw new ResourceNotFoundException("Nenhum associado encontrado com id " + body.getIdAssociado());
    }





}
