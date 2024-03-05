package com.softexpert.food.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TipoAcrescimoTest {

    @Test
    @DisplayName("getDescricao deve retornar descricao do ENUM se sucesso.")
    void getDescricao() {

        TipoAcrescimo inteiro = TipoAcrescimo.INTEIRO;

        assertThat(inteiro.getDescricao()).isEqualTo(TipoAcrescimo.INTEIRO.getDescricao());

    }
}