package br.com.votacao.entity;

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

    @JsonProperty("votos_totais")
    @Column(name = "votos_totais")
    private long totalVotos;

    @JsonProperty("votos_totais_sim")
    @Column(name = "votos_totais_sim")
    private long totalVotosSim;

    @JsonProperty("votos_totais_nao")
    @Column(name = "votos_totais_nao")
    private long totalVotosNao;

    public ResultadoPauta(long totalVotos, long totalVotosSim, long totalVotosNao) {
        this.totalVotos = totalVotos;
        this.totalVotosSim = totalVotosSim;
        this.totalVotosNao = totalVotosNao;
    }
}
