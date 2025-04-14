package med.voll.API.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.API.domain.endereco.Endereco;

public record DadosAtualizacaoPaciente(@NotNull
                                       Long id,
                                       String nome,
                                       String telefone,
                                       Endereco endereco) {
}
