package br.com.votacao.domain.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "resultado_pauta")
public class ResultadoPauta {

    @Id
    private String id = UUID.randomUUID().toString();

    @JsonProperty("pauta_id")
    @Column(name = "pauta_id")
    private String pautaId;

    @JsonProperty("votos_totais")
    @Column(name = "votos_totais")
    private long totalVotos;

    @JsonProperty("votos_totais_sim")
    @Column(name = "votos_totais_sim")
    private long totalVotosSim;

    @JsonProperty("votos_totais_nao")
    @Column(name = "votos_totais_nao")
    private long totalVotosNao;

    public ResultadoPauta(String pautaId, long totalVotos, long totalVotosSim, long totalVotosNao) {
        this.pautaId = pautaId;
        this.totalVotos = totalVotos;
        this.totalVotosSim = totalVotosSim;
        this.totalVotosNao = totalVotosNao;
    }
}
