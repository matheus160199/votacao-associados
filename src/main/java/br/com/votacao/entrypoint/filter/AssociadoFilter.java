package br.com.votacao.entrypoint.filter;

import br.com.votacao.domain.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AssociadoFilter {

    @Autowired
    private AssociadoRepository repository;

    public void validaNovoAssociado(final String cpf){
        if(repository.findByCpf(cpf).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um associado cadastrado com esse CPF!");

    }
}
