package com.softexpert.food.domain.model;

import org.junit.jupiter.api.Test;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PedidoTest {

    @Test
    public void testGetterAndSetter() {
        List<ItemPedido> itensPedido = new ArrayList<>();
        Pedido pedido = new Pedido(itensPedido, "João");

        assertThat(pedido.getItensPedido()).isEqualTo(itensPedido);
        assertThat(pedido.getNomeSolicitante()).isEqualTo("João");
    }

    @Test
    public void testConstrutores() {
        List<ItemPedido> itensPedido = new ArrayList<>();
        Pedido pedido = new Pedido(itensPedido, "Maria");

        assertThat(pedido.getItensPedido()).isEqualTo(itensPedido);
        assertThat(pedido.getNomeSolicitante()).isEqualTo("Maria");
    }

    @Test
    public void testToString() {
        List<ItemPedido> itensPedido = new ArrayList<>();
        itensPedido.add(new ItemPedido("Livro", BigDecimal.valueOf(20.0)));
        Pedido pedido = new Pedido(itensPedido, "Carlos");

        assertThat(pedido.toString()).isEqualTo("Pedido(itensPedido=[ItemPedido(tituloItem=Livro, valorItem=20.0)], nomeSolicitante=Carlos)");
    }

    @Test
    public void testEquals() {
        List<ItemPedido> itensPedido1 = new ArrayList<>();
        itensPedido1.add(new ItemPedido("Livro", BigDecimal.valueOf(20.0)));

        List<ItemPedido> itensPedido2 = new ArrayList<>();
        itensPedido2.add(new ItemPedido("Livro", BigDecimal.valueOf(20.0)));

        List<ItemPedido> itensPedido3 = new ArrayList<>();
        itensPedido3.add(new ItemPedido("Caneta", BigDecimal.valueOf(5.0)));

        Pedido pedido1 = new Pedido(itensPedido1, "Ana");
        Pedido pedido2 = new Pedido(itensPedido2, "Ana");
        Pedido pedido3 = new Pedido(itensPedido3, "Maria");

        assertThat(pedido1).isEqualTo(pedido2);
        assertThat(pedido1).isNotEqualTo(pedido3);
    }

    @Test
    public void testHashCode() {
        List<ItemPedido> itensPedido1 = new ArrayList<>();
        itensPedido1.add(new ItemPedido("Livro", BigDecimal.valueOf(20.0)));

        List<ItemPedido> itensPedido2 = new ArrayList<>();
        itensPedido2.add(new ItemPedido("Livro", BigDecimal.valueOf(20.0)));

        List<ItemPedido> itensPedido3 = new ArrayList<>();
        itensPedido3.add(new ItemPedido("Caneta", BigDecimal.valueOf(5.0)));

        Pedido pedido1 = new Pedido(itensPedido1, "Ana");
        Pedido pedido2 = new Pedido(itensPedido2, "Ana");
        Pedido pedido3 = new Pedido(itensPedido3, "Maria");

        assertThat(pedido1.hashCode()).isEqualTo(pedido2.hashCode());
        assertThat(pedido1.hashCode()).isNotEqualTo(pedido3.hashCode());
    }
}