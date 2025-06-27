package br.com.kaiomuniz.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    private Double precoUnitario;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;
}
