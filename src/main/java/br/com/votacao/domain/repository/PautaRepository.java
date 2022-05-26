package br.com.votacao.domain.repository;

import br.com.votacao.domain.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PautaRepository extends JpaRepository<Pauta, Long>, JpaSpecificationExecutor<Pauta> {

    Pauta save(final Pauta pauta);

    Optional<Pauta> findById(final String id);

    Optional<Pauta> findByNome(final String nome);
}

