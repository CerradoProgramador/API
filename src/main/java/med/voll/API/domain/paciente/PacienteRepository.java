package med.voll.API.domain.paciente;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByAtivoTrue(Pageable pageable);

    Optional<Paciente> findByCpf(String cpfPaciente);

    @Query("SELECT p.ativo FROM Paciente p WHERE p.id = :idPaciente")
    Boolean findAtivoById(Long idPaciente);
}
