package br.com.votacao.usecase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import br.com.votacao.application.usecase.CpfAPIUseCase;
import br.com.votacao.application.usecase.impl.AssociadoUseCaseImpl;
import br.com.votacao.application.usecase.impl.PautaUseCaseImpl;
import br.com.votacao.domain.entity.Associado;
import br.com.votacao.domain.entity.Pauta;
import br.com.votacao.domain.entity.Voto;
import br.com.votacao.domain.enumerated.TipoVoto;
import br.com.votacao.domain.repository.service.AssociadoRepositoryService;
import br.com.votacao.domain.repository.service.PautaRepositoryService;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class PautaUseCaseTest {

    private Pauta pauta;

    @Mock
    private PautaRepositoryService repository;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private PautaUseCaseImpl pautaUseCase;

    @Before
    public void init() {
        this.pauta = new Pauta("Pauta Teste");
    }

    @Test
    public void validaNovaPauta(){
        when(repository.salva(any(Pauta.class))).thenAnswer(answer -> {
            return answer.getArgument(0);
        });
        Pauta pauta = pautaUseCase.nova("Pauta Teste");
        assertNotNull(pauta);
        assertEquals(pauta.getNome(), "Pauta Teste");
    }

    @Test
    public void validaAberturaSessao(){
        when(repository.buscaPorId(anyString())).thenReturn(this.pauta);
        when(repository.salva(any(Pauta.class))).thenAnswer(answer -> {
            return answer.getArgument(0);
        });
        this.pauta = pautaUseCase.abreSessao(this.pauta.getId(), LocalDateTime.now().plusDays(10));
        assertNotNull(this.pauta.getDataExpiracaoSessao());
        assertTrue(this.pauta.isSessaoDisponivel());
    }

    @Test
    public void validaContabilizaVoto(){
        when(repository.buscaPorId(anyString())).thenReturn(this.pauta);
        when(repository.salva(any(Pauta.class))).thenAnswer(answer -> {
            return answer.getArgument(0);
        });
        this.pauta = pautaUseCase.contabilizaVoto(this.pauta.getId(), new Voto(TipoVoto.SIM, new Associado(""), pauta));
        assertFalse(this.pauta.getVotos().isEmpty());
    }
}
