package br.com.votacao.entrypoint.filter;

import br.com.votacao.domain.repository.VotoRepository;
import br.com.votacao.domain.repository.service.PautaRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class VotoFilter {

    @Autowired
    private VotoRepository repository;

    @Autowired
    private PautaRepositoryService pautaRepository;

    public void validaNovoVoto(final String pautaId, final String associadoId){
        if (!pautaRepository.buscaPorId(pautaId).isSessaoDisponivel())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sessão de votação para esta pauta não está disponivel!");
        if (repository.findByAssociadoAndPauta(associadoId, pautaId).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Associado já votou nessa pauta!");
    }
}
