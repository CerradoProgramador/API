package med.voll.API.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.API.domain.consulta.AgendaDeConsultas;
import med.voll.API.domain.consulta.DadosAgendamentoConsulta;
import med.voll.API.domain.consulta.DadosCancelamentoConsulta;
import med.voll.API.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

        @Autowired
        private AgendaDeConsultas agenda;

        @PostMapping
        @Transactional
        @Operation(security = { @SecurityRequirement(name = "bearer-key") })
        public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
            var dto =  agenda.agendar(dados);
            return ResponseEntity.ok(dto);
        }

    @DeleteMapping
    @Transactional
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }


}
