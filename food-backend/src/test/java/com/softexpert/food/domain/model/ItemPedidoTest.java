package com.softexpert.food.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class ItemPedidoTest {

    @Test
    public void testGetterAndSetter() {
        ItemPedido itemPedido = new ItemPedido("Livro", BigDecimal.valueOf(20.0));

        assertThat(itemPedido.getTituloItem()).isEqualTo("Livro");
        assertThat(itemPedido.getValorItem()).isEqualTo(BigDecimal.valueOf(20.0));
    }

    @Test
    public void testConstrutores() {
        ItemPedido itemPedido = new ItemPedido("Livro", BigDecimal.valueOf(20.0));

        assertThat(itemPedido.getTituloItem()).isEqualTo("Livro");
        assertThat(itemPedido.getValorItem()).isEqualTo(BigDecimal.valueOf(20.0));
    }

    @Test
    public void testToString() {
        ItemPedido itemPedido = new ItemPedido("Livro", BigDecimal.valueOf(20.0));

        assertThat(itemPedido.toString()).isEqualTo("ItemPedido(tituloItem=Livro, valorItem=20.0)");
    }

    @Test
    public void testEquals() {
        ItemPedido itemPedido1 = new ItemPedido("Livro", BigDecimal.valueOf(20.0));
        ItemPedido itemPedido2 = new ItemPedido("Livro", BigDecimal.valueOf(20.0));
        ItemPedido itemPedido3 = new ItemPedido("Caneta", BigDecimal.valueOf(20.0));

        assertThat(itemPedido1).isEqualTo(itemPedido2);
        assertThat(itemPedido1).isNotEqualTo(itemPedido3);
    }

    @Test
    public void testHashCode() {
        ItemPedido itemPedido1 = new ItemPedido("Livro", BigDecimal.valueOf(20.0));
        ItemPedido itemPedido2 = new ItemPedido("Livro", BigDecimal.valueOf(20.0));
        ItemPedido itemPedido3 = new ItemPedido("Caneta", BigDecimal.valueOf(5.0));

        assertThat(itemPedido1.hashCode()).isEqualTo(itemPedido2.hashCode());
        assertThat(itemPedido1.hashCode()).isNotEqualTo(itemPedido3.hashCode());
    }

}