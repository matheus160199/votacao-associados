package br.com.votacao.application.usecase.impl;

import br.com.votacao.application.usecase.PautaUseCase;
import br.com.votacao.application.usecase.VotoUseCase;
import br.com.votacao.domain.entity.Voto;
import br.com.votacao.domain.enumerated.TipoVoto;
import br.com.votacao.domain.repository.service.AssociadoRepositoryService;
import br.com.votacao.domain.repository.service.PautaRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoUseCaseImpl implements VotoUseCase {

    @Autowired
    private PautaUseCase pautaUseCase;

    @Autowired
    private AssociadoRepositoryService associadoRepository;

    @Autowired
    private PautaRepositoryService pautaRepository;

    @Override
    public Voto novo(String associadoId, TipoVoto tipoVoto, String pautaId) {
        Voto voto = new Voto(tipoVoto, associadoRepository.buscaPorId(associadoId), pautaRepository.buscaPorId(pautaId));
        pautaUseCase.contabilizaVoto(pautaId, voto);
        return voto;
    }
}
