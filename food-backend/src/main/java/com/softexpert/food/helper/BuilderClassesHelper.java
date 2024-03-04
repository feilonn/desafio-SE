package com.softexpert.food.helper;

import com.softexpert.food.domain.model.ItemPedido;
import com.softexpert.food.dto.PagamentoDetalhesDTO;

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

}
