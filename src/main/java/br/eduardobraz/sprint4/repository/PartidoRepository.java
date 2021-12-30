package br.eduardobraz.sprint4.repository;

import br.eduardobraz.sprint4.entity.IdeologyEnum;
import br.eduardobraz.sprint4.entity.Partido;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {

    Optional<Partido> findById(@Range(min = 1) Long id);
    Optional<List<Partido>> findByIdeologyEquals(IdeologyEnum ideology);
}
