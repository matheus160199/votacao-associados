package br.com.votacao.application.usecase.impl;

import br.com.votacao.application.usecase.AssociadoUseCase;
import br.com.votacao.application.usecase.CpfAPIUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.votacao.domain.entity.Associado;
import br.com.votacao.domain.repository.service.AssociadoRepositoryService;


@Service
public class AssociadoUseCaseImpl implements AssociadoUseCase {

    @Autowired
    private AssociadoRepositoryService repository;
    
    @Autowired
    private CpfAPIUseCase cpfAPIUseCase;

    @Override
    public Associado novo(String cpf) {
        cpfAPIUseCase.validaCpf(cpf);
        return repository.salva(new Associado(cpf));
    }
}
