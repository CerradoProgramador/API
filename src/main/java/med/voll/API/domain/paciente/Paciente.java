package med.voll.API.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.API.domain.endereco.Endereco;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private boolean ativo;
    @Embedded
    private Endereco endereco;



    public Paciente(DadosCadastroPaciente dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.ativo = true;
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoPaciente dados) {
            this.nome = dados.nome();
            this.telefone = dados.telefone();
            this.endereco = dados.endereco();
    }

    public void excluir() {
        this.ativo = false;
    }
}
