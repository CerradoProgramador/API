package med.voll.API.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.API.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco

) {
}
