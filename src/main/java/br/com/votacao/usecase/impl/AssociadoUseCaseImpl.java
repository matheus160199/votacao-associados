package br.com.votacao.usecase.impl;

import br.com.votacao.entity.Associado;
import br.com.votacao.repository.service.AssociadoRepositoryService;
import br.com.votacao.usecase.AssociadoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoUseCaseImpl implements AssociadoUseCase {

    @Autowired
    private AssociadoRepositoryService repository;

    @Override
    public Associado novo(String cpf) {
        return repository.salva(new Associado(cpf));
    }
}
