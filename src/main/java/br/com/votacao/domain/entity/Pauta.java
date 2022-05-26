package br.com.votacao.domain.entity;

import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;
import br.com.votacao.domain.enumerated.TipoVoto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pauta")
public class Pauta {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(unique = true)
    private String nome;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Voto> votos = new ArrayList<>();

    @JsonProperty("sessao_disponivel")
    @Column(name = "sessao_disponivel")
    private boolean sessaoDisponivel = false;

    @JsonProperty("data_expiracao_sessao")
    @Column(name = "data_expiracao_sessao")
    private LocalDateTime dataExpiracaoSessao;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ResultadoPauta resultado;

    public Pauta(String nome) {
        this.nome = nome;
    }

    public void abreSessao(LocalDateTime dataExpiracaoSessao) {
        if (dataExpiracaoSessao == null)
            dataExpiracaoSessao = LocalDateTime.now().plusMinutes(1);
        this.dataExpiracaoSessao = dataExpiracaoSessao;
        this.sessaoDisponivel = true;
    }

    public void finalizaSessao() {
        this.sessaoDisponivel = false;
        this.contabilizaResultado();
    }

    public void contabilizaResultado() {
        long votosSim = this.votos.stream().filter(v -> v.getTipoVoto() == TipoVoto.SIM).count();
        long votosNao = this.votos.stream().filter(v -> v.getTipoVoto() == TipoVoto.NAO).count();

        this.resultado = new ResultadoPauta(this.id, votosSim + votosNao, votosSim, votosNao);
    }

    public void adicionaVoto(Voto voto) {
        this.votos.add(voto);
    }
}
