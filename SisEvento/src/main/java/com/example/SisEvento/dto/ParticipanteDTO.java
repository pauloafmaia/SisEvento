package com.example.SisEvento.dto;

import lombok.Data;

@Data
public class ParticipanteDTO {

    private Long id;
    private String nome;
    private String apelido;
    private String dataNascimento;
    private String sexo;
    private String email;
    private String whatsapp;
    private String nomeMae;
    private String foneMae;
    private String nomePai;
    private String fonePai;
    private String pessoaContato;
    private String foneContato;
    private String medicacao;
    private String alergia;
    private String restricaoAlimentar;
}
