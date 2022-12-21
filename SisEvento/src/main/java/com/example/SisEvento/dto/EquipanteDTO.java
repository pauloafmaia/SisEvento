package com.example.SisEvento.dto;

import lombok.Data;

@Data
public class EquipanteDTO {

    private Long id;
    private String nome;
    private String apelido;
    private String dataNascimento;
    private String sexo;
    private String email;
    private String whatsapp;
    private String medicacao;
    private String alergia;
    private String restricaoAlimentar;
}
