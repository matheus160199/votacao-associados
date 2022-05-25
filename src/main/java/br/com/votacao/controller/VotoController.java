package br.com.votacao.controller;

import br.com.votacao.entity.Voto;
import br.com.votacao.enumerated.TipoVoto;
import br.com.votacao.controller.filter.VotoFilter;
import br.com.votacao.usecase.VotoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@Configuration
@Validated
@RestController
@RequestMapping("/v1/voto")
public class VotoController {

    @Autowired
    private VotoUseCase useCase;

    @Autowired
    private VotoFilter filter;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Cria um novo voto em uma pauta")
    @PostMapping("/novo")
    public Voto novo(@RequestParam(name = "associado_id", required = true) @NotBlank(message = "ID de associado é obrigatório!") final String associadoId,
                     @RequestParam(name = "voto", required = true) final TipoVoto voto,
                     @RequestParam(name = "pauta_id", required = true) @NotBlank(message = "ID de pauta é obrigatório!") final String pautaId) {
        filter.validaNovoVoto(pautaId, associadoId);
        return useCase.novo(associadoId, voto, pautaId);
    }

}