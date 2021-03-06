package br.com.votacao.domain.entity;

import br.com.votacao.domain.enumerated.TipoVoto;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;


@RunWith(MockitoJUnitRunner.class)
public class PautaTest {

    private Pauta pauta;

    @Before
    public void init(){
        pauta = new Pauta("Pauta Teste");
    }

    @Test
    public void validaAberturaSessao(){
        LocalDateTime data = LocalDateTime.now().plusDays(10);
        pauta.abreSessao(data);
        assertEquals(pauta.getDataExpiracaoSessao(), data);
        assertTrue(pauta.isSessaoDisponivel());
    }

    @Test
    public void validaFinalizaSessao(){
        LocalDateTime data = LocalDateTime.now().plusDays(10);
        pauta.abreSessao(data);
        pauta.finalizaSessao();
        assertFalse(pauta.isSessaoDisponivel());
    }

    @Test
    public void validaContabilizaResultado(){
        pauta.adicionaVoto(new Voto(TipoVoto.SIM, new Associado("45696563"), pauta));
        pauta.contabilizaResultado();
        assertNotNull(pauta.getResultado());
        assertEquals(pauta.getResultado().getTotalVotos(), 1);
        assertEquals(pauta.getResultado().getTotalVotosNao(), 0);
        assertEquals(pauta.getResultado().getTotalVotosSim(), 1);
    }

    @Test
    public void validaAdiconaVoto(){
        pauta.adicionaVoto(new Voto(TipoVoto.SIM, new Associado("45696563"), pauta));
        assertFalse(pauta.getVotos().isEmpty());
    }

}
