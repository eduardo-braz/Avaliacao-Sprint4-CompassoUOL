package br.eduardobraz.sprint4.repository;

import br.eduardobraz.sprint4.entity.Associate;
import br.eduardobraz.sprint4.entity.CargoPoliticoEnum;
import br.eduardobraz.sprint4.entity.Partido;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {

    Optional<List<Associate>> findByCargoOrderByNameDesc(CargoPoliticoEnum cargoPoliticoEnum);

    Optional<List<Associate>> findByCargoOrderByNameAsc(CargoPoliticoEnum cargoPoliticoEnum);

    Optional<Associate> findById(@Range(min = 1) Long id);

}
