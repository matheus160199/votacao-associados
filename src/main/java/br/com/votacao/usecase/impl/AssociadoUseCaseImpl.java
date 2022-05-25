package br.com.votacao.usecase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.votacao.entity.Associado;
import br.com.votacao.repository.service.AssociadoRepositoryService;
import br.com.votacao.usecase.AssociadoUseCase;
import br.com.votacao.usecase.CpfAPIUseCase;

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
