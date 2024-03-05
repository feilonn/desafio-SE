package com.softexpert.food.helper;

import com.softexpert.food.domain.model.ItemPedido;
import com.softexpert.food.domain.model.Pedido;
import com.softexpert.food.dto.CarrinhoDTO;
import com.softexpert.food.dto.PagamentoDetalhesDTO;
import com.softexpert.food.dto.PixChargeDTO;
import com.softexpert.food.enums.TipoAcrescimo;
import com.softexpert.food.enums.TipoDesconto;
import com.softexpert.food.enums.TipoPagamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuilderClassesHelper {

    public static List<ItemPedido> criarItensPedidoDTO(List<ItemPedido> itensPedido) {
        return itensPedido.stream()
                .map(itemPedido -> ItemPedido.builder()
                        .tituloItem(itemPedido.getTituloItem())
                        .valorItem(itemPedido.getValorItem())
                        .build())
                .collect(Collectors.toList());
    }

    public static PagamentoDetalhesDTO criarDetalhesItemSolicitado(String nomeSolicitante, List<ItemPedido> itensPedido,
                                                                   String valorTratado) {
        return PagamentoDetalhesDTO.builder()
                .itensPedido(itensPedido)
                .nomeSolicitante(nomeSolicitante)
                .valorFinalParaPagar(valorTratado)
                .build();
    }

    public static List<PagamentoDetalhesDTO> criarListDetalhesItemSolicitado() {
        PagamentoDetalhesDTO dto = PagamentoDetalhesDTO.builder()
                .nomeSolicitante("solicitante")
                .build();

        List<PagamentoDetalhesDTO> list = new ArrayList<>();

        list.add(dto);

        return list;
    }

    public static List<ItemPedido> criarListItemPedido() {
        List<ItemPedido> list = new ArrayList<>();

        ItemPedido itemPedido = ItemPedido.builder()
                .tituloItem("Lanche")
                .valorItem(BigDecimal.valueOf(1.0))
                .build();

        list.add(itemPedido);

        return list;
    }

    public static List<Pedido> criarListPedidoValido() {
        List<Pedido> list = new ArrayList<>();

        Pedido pedido = Pedido.builder()
                .itensPedido(criarListItemPedido())
                .nomeSolicitante("Solicitante")
                .build();

        list.add(pedido);

        return list;
    }

    public static CarrinhoDTO criarCarrinhoDTOValido() {
        CarrinhoDTO dto = CarrinhoDTO.builder()
                .acrescimo(BigDecimal.valueOf(0.0))
                .desconto(BigDecimal.valueOf(0.0))
                .valorFrete(BigDecimal.valueOf(0.0))
                .tipoAcrescimo(TipoAcrescimo.INTEIRO)
                .tipoDesconto(TipoDesconto.INTEIRO)
                .tipoPagamento(TipoPagamento.PIX)
                .pedidos(criarListPedidoValido())
                .build();

        return dto;
    }

    public static PixChargeDTO criarPixChargeDTOValido() {
        PixChargeDTO dto = PixChargeDTO.builder()
                .valor("1.00")
                .chave("chave_pix")
                .nomePagador("Solicitante")
                .build();

        return dto;
    }


}
