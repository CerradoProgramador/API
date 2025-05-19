package med.voll.API.domain.consulta.validacao;

import jakarta.validation.ValidationException;
import med.voll.API.domain.consulta.DadosAgendamentoConsulta;
import med.voll.API.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados){
        if (dados.idMedico() == null){
            return;
        }

        var idMedico = dados.idMedico();
        var medicoEstaAtivo = medicoRepository.findAtivoById(idMedico);
        if (!medicoEstaAtivo){
            throw new ValidacaoException("Este medico não está ativo!");
        }

    }

}
