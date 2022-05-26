package br.com.votacao.usecase.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import br.com.votacao.entity.Pauta;
import br.com.votacao.entity.Voto;
import br.com.votacao.repository.service.PautaRepositoryService;
import br.com.votacao.usecase.PautaUseCase;

@Service
public class PautaUseCaseImpl implements PautaUseCase {

    @Autowired
    private PautaRepositoryService repository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public Pauta nova(String nome) {
        return repository.salva(new Pauta(nome));
    }

    @Override
    public Pauta abreSessao(String id, LocalDateTime dataExpiracao) {
        Pauta pauta = repository.buscaPorId(id);
        pauta.abreSessao(dataExpiracao);
        agendaFimSessao(pauta);
        return repository.salva(pauta);
    }

    @Override
    public Pauta contabilizaVoto(String id, Voto voto) {
        Pauta pauta = repository.buscaPorId(id);
        pauta.adicionaVoto(voto);
        return repository.salva(pauta);
    }

    private void agendaFimSessao(Pauta pauta) {
        new Timer().schedule(finalizaSessao(pauta), Date.from(pauta.getDataExpiracaoSessao().atZone(ZoneId.systemDefault()).toInstant()));
    }

    private TimerTask finalizaSessao(Pauta pauta) {
        return new TimerTask() {
            @Override
            public void run() {
                pauta.finalizaSessao();
                repository.salva(pauta);
                kafkaTemplate.send("resultado-pautas-topic", pauta);
            }
        };
    }
}
