package com.softexpert.food.enums;

public enum TipoAcrescimo {
    PORCENTAGEM("Porcentagem"),
    INTEIRO("Inteiro");

    private final String descricao;

    TipoAcrescimo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
