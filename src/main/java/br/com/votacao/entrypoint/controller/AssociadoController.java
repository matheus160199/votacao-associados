package br.com.votacao.entrypoint.controller;

import br.com.votacao.application.usecase.AssociadoUseCase;
import br.com.votacao.domain.entity.Associado;
import br.com.votacao.entrypoint.filter.AssociadoFilter;
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
@RequestMapping("/v1/associado")
public class AssociadoController {

    @Autowired
    private AssociadoUseCase useCase;

    @Autowired
    private AssociadoFilter filter;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Cria um novo associado")
    @PostMapping("/novo")
    public Associado novo(@RequestParam(name = "cpf", required = true) @NotBlank(message = "CPF é obrigatório!") final String cpf) {
        filter.validaNovoAssociado(cpf);
        return useCase.novo(cpf);
    }
}
