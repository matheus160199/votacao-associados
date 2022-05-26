package br.com.votacao.entrypoint.controller;

import br.com.votacao.application.usecase.PautaUseCase;
import br.com.votacao.domain.entity.Pauta;
import br.com.votacao.entrypoint.filter.PautaFilter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Configuration
@Validated
@RestController
@RequestMapping("/v1/pauta")
public class PautaController {

    @Autowired
    private PautaUseCase useCase;

    @Autowired
    private PautaFilter filter;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Cria uma nova pauta")
    @PostMapping("/nova")
    public Pauta nova(@RequestParam(name = "nome", required = true) @NotBlank(message = "Nome de pauta é obrigatório!") final String nome) {
        filter.validaNovaPauta(nome);
        return useCase.nova(nome);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Abre a sessão de votos de uma pauta")
    @PutMapping("/abre-sessao")
    public Pauta abreSessao(@RequestParam(name = "id", required = true) @NotBlank(message = "ID da pauta é obrigatório!") final String id,
                                 @RequestParam(name = "data_expiracao", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") final LocalDateTime dataExpiracao) {
        filter.validaAberturaSessao(id, dataExpiracao);
        return useCase.abreSessao(id, dataExpiracao);
    }

}