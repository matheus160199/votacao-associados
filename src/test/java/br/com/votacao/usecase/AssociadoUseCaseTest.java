package br.com.votacao.usecase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import br.com.votacao.application.usecase.CpfAPIUseCase;
import br.com.votacao.application.usecase.impl.AssociadoUseCaseImpl;
import br.com.votacao.domain.entity.Associado;
import br.com.votacao.domain.repository.service.AssociadoRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AssociadoUseCaseTest {

    @Mock
    private AssociadoRepositoryService repository;

    @Mock
    private CpfAPIUseCase cpfAPIUseCase;

    @InjectMocks
    private AssociadoUseCaseImpl associadoUseCase;

    @Test
    public void validaNovoAssociado(){
        doNothing().when(cpfAPIUseCase).validaCpf(anyString());
        when(repository.salva(any(Associado.class))).thenAnswer(answer -> {
            return answer.getArgument(0);
        });
        Associado associado = associadoUseCase.novo("123456");
        assertNotNull(associado);
        assertEquals(associado.getCpf(), "123456");
    }
}
