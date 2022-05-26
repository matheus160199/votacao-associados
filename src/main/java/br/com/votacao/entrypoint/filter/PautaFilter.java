package br.com.votacao.entrypoint.filter;

import br.com.votacao.domain.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Component
public class PautaFilter {

    @Autowired
    private PautaRepository repository;

    public void validaNovaPauta (final String nome){
        if (repository.findByNome(nome).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma pauta criada com esse nome!");
    }

    public void validaAberturaSessao (final String pautaId, final LocalDateTime dataExpiracao){
        if (dataExpiracao.isBefore(LocalDateTime.now()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de expiração de sessão inválida!");
    }
}
