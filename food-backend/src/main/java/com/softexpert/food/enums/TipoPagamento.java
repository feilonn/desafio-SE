package com.softexpert.food.enums;

public enum TipoPagamento {
    PIX("Pix");

    private final String descricao;

    TipoPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
