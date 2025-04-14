package med.voll.API.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query("SELECT m FROM Medico m WHERE m.nome ILIKE :nomeMedico")
    Optional<Medico> FindByNome(String nomeMedico);
}
