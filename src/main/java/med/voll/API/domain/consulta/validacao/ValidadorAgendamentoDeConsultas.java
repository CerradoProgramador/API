package med.voll.API.domain.consulta.validacao;

import med.voll.API.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;


@Component
public interface ValidadorAgendamentoDeConsultas {

    void validar(DadosAgendamentoConsulta dados);
}
