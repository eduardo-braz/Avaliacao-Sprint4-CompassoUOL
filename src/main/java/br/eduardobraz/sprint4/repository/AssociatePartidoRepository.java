package br.eduardobraz.sprint4.repository;

import br.eduardobraz.sprint4.entity.Associate;
import br.eduardobraz.sprint4.entity.AssociatePartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssociatePartidoRepository  extends JpaRepository<AssociatePartido, Long> {


    Optional<AssociatePartido> findByAssociateIdAndPartidoId(Long idAssociate, Long idPartido);

    Optional<List<AssociatePartido>> findByPartidoId(Long idPartido);

}
