package br.com.votacao.usecase;

import br.com.votacao.entity.Associado;

public interface AssociadoUseCase {

    Associado novo(final String cpf);
}
