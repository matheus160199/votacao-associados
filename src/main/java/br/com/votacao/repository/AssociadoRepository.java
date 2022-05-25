package br.com.votacao.repository;

import br.com.votacao.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AssociadoRepository extends JpaRepository<Associado, Long>, JpaSpecificationExecutor<Associado>{

    Associado save(final Associado Associado);

    Optional<Associado> findById(final String id);

    Optional<Associado> findByCpf(final String cpf);
}