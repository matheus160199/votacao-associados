package br.com.votacao.application.usecase;

import br.com.votacao.domain.entity.Voto;
import br.com.votacao.domain.enumerated.TipoVoto;

public interface VotoUseCase {

    Voto novo(final String cpf, final TipoVoto voto, final String pautaId);

}

