package br.com.votacao.application.usecase;

import br.com.votacao.domain.entity.Associado;

public interface AssociadoUseCase {

    Associado novo(final String cpf);
}
