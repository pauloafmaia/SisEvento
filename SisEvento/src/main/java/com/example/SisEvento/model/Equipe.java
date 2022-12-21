package com.example.SisEvento.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToOne(mappedBy = "equipe")
    private Equipante equipante;
}
