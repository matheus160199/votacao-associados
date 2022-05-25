package br.com.votacao.usecase.impl;

import br.com.votacao.entity.Associado;
import br.com.votacao.entity.Voto;
import br.com.votacao.enumerated.TipoVoto;
import br.com.votacao.repository.service.AssociadoRepositoryService;
import br.com.votacao.repository.service.VotoRepositoryService;
import br.com.votacao.usecase.PautaUseCase;
import br.com.votacao.usecase.VotoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoUseCaseImpl implements VotoUseCase {

    @Autowired
    private VotoRepositoryService repository;

    @Autowired
    private PautaUseCase pautaUseCase;

    @Autowired
    private AssociadoRepositoryService associadoRepository;

    @Override
    public Voto novo(String associadoId, TipoVoto tipoVoto, String pautaId) {
        Associado associado = associadoRepository.buscaPorId(associadoId);
        Voto voto = new Voto(tipoVoto, associado.getId(), pautaId);
        pautaUseCase.contabilizaVoto(pautaId, voto);
        return repository.salva(voto);
    }
}
