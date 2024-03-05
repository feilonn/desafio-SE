package com.softexpert.food.dto;

import com.softexpert.food.domain.model.ItemPedido;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class PagamentoDetalhesDTOTest {

    @Test
    public void testGetterAndSetter() {
        List<ItemPedido> itensPedido = new ArrayList<>();
        PagamentoDetalhesDTO pagamentoDetalhesDTO = new PagamentoDetalhesDTO(itensPedido,
                "João", "100.00", "http://example.com");

        assertThat(pagamentoDetalhesDTO.getItensPedido()).isEqualTo(itensPedido);
        assertThat(pagamentoDetalhesDTO.getNomeSolicitante()).isEqualTo("João");
        assertThat(pagamentoDetalhesDTO.getValorFinalParaPagar()).isEqualTo("100.00");
        assertThat(pagamentoDetalhesDTO.getUrlPix()).isEqualTo("http://example.com");
    }

    @Test
    public void testConstrutores() {
        List<ItemPedido> itensPedido = new ArrayList<>();
        PagamentoDetalhesDTO pagamentoDetalhesDTO = new PagamentoDetalhesDTO(itensPedido,
                "Maria", "150.00", "http://example.com");

        assertThat(pagamentoDetalhesDTO.getItensPedido()).isEqualTo(itensPedido);
        assertThat(pagamentoDetalhesDTO.getNomeSolicitante()).isEqualTo("Maria");
        assertThat(pagamentoDetalhesDTO.getValorFinalParaPagar()).isEqualTo("150.00");
        assertThat(pagamentoDetalhesDTO.getUrlPix()).isEqualTo("http://example.com");
    }

    @Test
    public void testToString() {
        List<ItemPedido> itensPedido = new ArrayList<>();

        ItemPedido itemPedido = ItemPedido.builder()
                .tituloItem("Livro")
                .valorItem(BigDecimal.valueOf(20.0))
                .build();

        itensPedido.add(itemPedido);
        PagamentoDetalhesDTO pagamentoDetalhesDTO = new PagamentoDetalhesDTO(itensPedido,
                "Carlos", "50.00", "http://example.com");

        assertThat(pagamentoDetalhesDTO.toString()).isEqualTo("PagamentoDetalhesDTO" +
                "(itensPedido=[ItemPedido(tituloItem=Livro, valorItem=20.0)], nomeSolicitante=Carlos," +
                " valorFinalParaPagar=50.00, urlPix=http://example.com)");
    }

    @Test
    public void testEquals() {

        List<ItemPedido> itensPedidoUm = new ArrayList<>();
        List<ItemPedido> itensPedidoDois = new ArrayList<>();

        ItemPedido itemPedidoUm = ItemPedido.builder()
                .tituloItem("Livro")
                .valorItem(BigDecimal.valueOf(20.0))
                .build();

        ItemPedido itemPedidoDois = ItemPedido.builder()
                .tituloItem("Livro")
                .valorItem(BigDecimal.valueOf(20.0))
                .build();

        itensPedidoUm.add(itemPedidoUm);

        itensPedidoDois.add(itemPedidoDois);

        PagamentoDetalhesDTO pagamentoDetalhesDTO1 = new PagamentoDetalhesDTO(itensPedidoUm,
                "Ana", "70.00", "http://example.com");

        PagamentoDetalhesDTO pagamentoDetalhesDTO2 = new PagamentoDetalhesDTO(itensPedidoDois,
                "Ana", "70.00", "http://example.com");

        assertThat(pagamentoDetalhesDTO1).isEqualTo(pagamentoDetalhesDTO2);
    }

    @Test
    public void testHashCode() {
        List<ItemPedido> itensPedidoUm = new ArrayList<>();
        List<ItemPedido> itensPedidoDois = new ArrayList<>();

        ItemPedido itemPedidoUm = ItemPedido.builder()
                .tituloItem("Livro")
                .valorItem(BigDecimal.valueOf(20.0))
                .build();

        ItemPedido itemPedidoDois = ItemPedido.builder()
                .tituloItem("Livro")
                .valorItem(BigDecimal.valueOf(20.0))
                .build();

        itensPedidoUm.add(itemPedidoUm);

        itensPedidoDois.add(itemPedidoDois);

        PagamentoDetalhesDTO pagamentoDetalhesDTO1 = new PagamentoDetalhesDTO(itensPedidoUm,
                "Ana", "70.00", "http://example.com");

        PagamentoDetalhesDTO pagamentoDetalhesDTO2 = new PagamentoDetalhesDTO(itensPedidoDois,
                "Ana", "70.00", "http://example.com");

        assertThat(pagamentoDetalhesDTO1.hashCode()).isEqualTo(pagamentoDetalhesDTO2.hashCode());
    }
}