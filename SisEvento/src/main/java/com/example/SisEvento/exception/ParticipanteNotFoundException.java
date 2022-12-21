package com.example.SisEvento.exception;

public class ParticipanteNotFoundException extends RuntimeException{

    public ParticipanteNotFoundException(String mensagem) {
        super(mensagem);
    }
}
