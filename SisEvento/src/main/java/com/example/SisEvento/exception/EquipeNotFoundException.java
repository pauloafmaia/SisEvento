package com.example.SisEvento.exception;

public class EquipeNotFoundException extends RuntimeException{

    public EquipeNotFoundException(String mensagem) {
        super(mensagem);
    }
}
