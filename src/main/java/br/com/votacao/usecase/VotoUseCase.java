package br.com.votacao.usecase;

import br.com.votacao.entity.Voto;
import br.com.votacao.enumerated.TipoVoto;

public interface VotoUseCase {

    Voto novo(final String cpf, final TipoVoto voto, final String pautaId);

}

