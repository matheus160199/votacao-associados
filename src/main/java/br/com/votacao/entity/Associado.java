package br.com.votacao.entity;

import javax.persistence.*;

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

    public Associado(String cpf) {
        this.cpf = cpf;
    }}
