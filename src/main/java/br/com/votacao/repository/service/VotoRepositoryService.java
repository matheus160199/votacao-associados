package br.com.votacao.repository.service;

import br.com.votacao.entity.Associado;
import br.com.votacao.entity.Voto;
import br.com.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VotoRepositoryService {

    @Autowired
    private VotoRepository repository;

    public Voto salva(Voto voto){
        return repository.save(voto);
    }

    public Voto buscaPorId(final String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe uma voto com o ID informado!"));
    }

}
