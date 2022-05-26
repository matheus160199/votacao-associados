package br.com.votacao.usecase;

import br.com.votacao.application.usecase.impl.PautaUseCaseImpl;
import br.com.votacao.application.usecase.impl.VotoUseCaseImpl;
import br.com.votacao.domain.entity.Associado;
import br.com.votacao.domain.entity.Pauta;
import br.com.votacao.domain.entity.Voto;
import br.com.votacao.domain.enumerated.TipoVoto;
import br.com.votacao.domain.repository.service.AssociadoRepositoryService;
import br.com.votacao.domain.repository.service.PautaRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VotoUseCaseTest {

    @Mock
    private AssociadoRepositoryService associadoRepository;

    @Mock
    private PautaRepositoryService pautaRepository;

    @Mock
    private PautaUseCaseImpl pautaUseCase;

    @InjectMocks
    private VotoUseCaseImpl votoUseCase;

    @Test
    public void validaNovoVoto(){
        Pauta pauta = new Pauta("Pauta Teste");
        when(pautaUseCase.contabilizaVoto(anyString(), any(Voto.class))).thenReturn(pauta);
        when(associadoRepository.buscaPorId(anyString())).thenReturn(new Associado("0000"));
        when(pautaRepository.buscaPorId(anyString())).thenReturn(pauta);

        Voto voto = votoUseCase.novo("123456", TipoVoto.SIM, "654321");
        assertNotNull(voto);
        assertEquals(voto.getTipoVoto(), TipoVoto.SIM);
        assertEquals(voto.getAssociado().getCpf(), "0000");
        assertEquals(voto.getPauta().getNome(), "Pauta Teste");
    }
}
