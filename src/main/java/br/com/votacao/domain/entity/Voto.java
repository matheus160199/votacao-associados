package br.com.votacao.domain.entity;

import java.util.UUID;

import javax.persistence.*;

import br.com.votacao.domain.enumerated.TipoVoto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "associado_id")
    @JsonBackReference
    @ToString.Exclude
    private Associado associado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pauta_id")
    @JsonBackReference
    @ToString.Exclude
    private Pauta pauta;


    public Voto(TipoVoto tipoVoto, Associado associado, Pauta pauta) {
        this.tipoVoto = tipoVoto;
        this.associado = associado;
        this.pauta = pauta;
    }
}
