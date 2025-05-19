package med.voll.API.domain.medico;

import jakarta.persistence.EntityManager;
import med.voll.API.domain.consulta.Consulta;
import med.voll.API.domain.endereco.DadosEndereco;
import med.voll.API.domain.paciente.DadosCadastroPaciente;
import med.voll.API.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Profile("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Deveria Devolver null quando o médico cadastrado não está disponivel na data")
    void buscarMedicoDisponivelParaDataCenario1() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var paciente = cadastrarPaciente("Paciente");
        var medico = cadastrarMedico("Medico", Especialidade.CARDIOLOGIA);
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        var medicoLivre = medicoRepository.buscarMedicoDisponivelParaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria Devolver Medico quando ele estiver disponivel na data")
    void buscarMedicoDisponivelParaDataCenario2() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);


        var medico = cadastrarMedico("Medico", Especialidade.CARDIOLOGIA);

        var medicoLivre = medicoRepository.buscarMedicoDisponivelParaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data, null));
    }

    private Medico cadastrarMedico(String nome, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome) {
        var paciente = new Paciente(dadosPaciente(nome));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                "medico@voll.med.com",
                "61999999999",
                "123456",
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome) {
        return new DadosCadastroPaciente(
                nome,
                "paciente@voll.med.com",
                "61999999999",
                "00000000000",
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}