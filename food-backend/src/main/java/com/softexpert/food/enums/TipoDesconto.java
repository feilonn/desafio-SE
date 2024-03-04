package com.softexpert.food.enums;

public enum TipoDesconto {
    PORCENTAGEM("Porcentagem"),
    INTEIRO("Inteiro");

    private final String descricao;

    TipoDesconto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
