package med.voll.API.domain.consulta.validacao;

import jakarta.validation.ValidationException;
import med.voll.API.domain.consulta.ConsultaRepository;
import med.voll.API.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorHorarioDisponivelMedico implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiConsultaNoMesmoHorario = consultaRepository.existsByMedico_IdAndData(dados.idMedico(), dados.data());
        if(medicoPossuiConsultaNoMesmoHorario){
            throw new ValidacaoException("Medico ja possui consulta no mesmo horario");
        }
    }
}
