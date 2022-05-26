package br.com.votacao.application.usecase;

import br.com.votacao.domain.entity.Pauta;
import br.com.votacao.domain.entity.Voto;

import java.time.LocalDateTime;

public interface PautaUseCase {

    Pauta nova(final String nome);

    Pauta abreSessao(final String id, final LocalDateTime dataExpiracao);

    Pauta contabilizaVoto(final String id, final Voto voto);

}
