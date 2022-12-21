package com.example.SisEvento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Equipante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo nome não pode ser em branco")
    @Size(min = 1, max = 60)
    private String nome;

    @NotEmpty(message = "O campo apelido não pode ser em branco")
    @Size(min = 1, max = 60)
    private String apelido;

    @NotEmpty(message = "O campo data de nascimento não pode ser em branco")
    @Size(min = 1, max = 60)
    private String dataNascimento;

    @NotEmpty(message = "O campo sexo não pode ser em branco")
    private String sexo;

    @NotEmpty(message = "O campo email não pode ser em branco")
    @Size(min = 1, max = 60)
    @Email(message = "O email digitado precisa ser válido")
    private String email;

    @NotEmpty(message = "O WhatsApp não pode ser em branco")
    @Size(min = 1, max = 60)
    private String whatsapp;

    @NotEmpty(message = "O campo de medicação não pode ser em branco")
    @Size(min = 1, max = 60)
    private String medicacao;

    @NotEmpty(message = "O campo de alergia não pode ser em branco")
    @Size(min = 1, max = 60)
    private String alergia;

    @NotEmpty(message = "O campo de restrição alimentar não pode ser em branco")
    @Size(min = 1, max = 60)
    private String restricaoAlimentar;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipe_equipante")
    private Equipe equipe;
}
