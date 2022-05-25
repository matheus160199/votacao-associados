package br.com.votacao.entity;

import java.util.UUID;

import javax.persistence.*;

import br.com.votacao.enumerated.TipoVoto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "voto")
public class Voto {

    @Id
    private String id = UUID.randomUUID().toString();

    @JsonProperty("tipo_voto")
    @Column(name = "tipo_voto")
    @Enumerated(EnumType.STRING)
    private TipoVoto tipoVoto;

    @JsonProperty("associado_id")
    @Column(name = "associado_id")
    private String associadoId;

    @JsonProperty("pauta_id")
    @Column(name = "pauta_id")
    private String pautaId;

    public Voto(TipoVoto tipoVoto, String associadoId, String pautaId) {
        this.tipoVoto = tipoVoto;
        this.associadoId = associadoId;
        this.pautaId = pautaId;
    }
}
