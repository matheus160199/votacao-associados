package br.com.votacao.domain.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "associado")
public class Associado {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(unique = true)
    private String cpf;

    @OneToMany(mappedBy = "associado", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Voto> votos = new ArrayList<>();

    public Associado(String cpf) {
        this.cpf = cpf;
    }}
