package med.voll.API.controller;

import jakarta.validation.Valid;
import med.voll.API.domain.usuario.DadosAutenticacao;
import med.voll.API.domain.usuario.Usuario;
import med.voll.API.infra.security.DadosTokenJwt;
import med.voll.API.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());

            return ResponseEntity.ok(new DadosTokenJwt(tokenJwt));
    }

}
