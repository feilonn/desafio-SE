package com.softexpert.food.service;

import com.softexpert.food.domain.exception.ValorDecimalNegativoException;
import com.softexpert.food.dto.CarrinhoDTO;
import com.softexpert.food.domain.model.ItemPedido;
import com.softexpert.food.dto.PagamentoDetalhesDTO;
import com.softexpert.food.domain.model.Pedido;
import com.softexpert.food.dto.PixChargeDTO;
import com.softexpert.food.enums.TipoAcrescimo;
import com.softexpert.food.enums.TipoDesconto;
import com.softexpert.food.enums.TipoPagamento;
import com.softexpert.food.helper.BuilderClassesHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PixService pixService;

    @Value("${pix.chave}")
    private String chavePix;

    public List<PagamentoDetalhesDTO> calcularPagamento(CarrinhoDTO carrinho) {

        List<PagamentoDetalhesDTO> detalhesPagamento = new ArrayList<>();

        double totalCompraSemFreteEDescontos = calcularTotalCompra(carrinho.getPedidos());

        double valorAcrescimoTratado = calculaValorAcrescimo(carrinho.getAcrescimo(), totalCompraSemFreteEDescontos,
                carrinho.getTipoAcrescimo());

        double valorDescontoTratado = calculaValorDesconto(carrinho.getDesconto(), totalCompraSemFreteEDescontos,
                carrinho.getTipoDesconto());

        carrinho.getPedidos().forEach(pedido -> {

            double totalPedido = calcularTotalPedido(pedido.getItensPedido());

            double proporcaoItens = calcularProporcao(totalPedido, totalCompraSemFreteEDescontos);

            double valorEntregaProporcional = calcularValorEntregaProporcional(carrinho.getValorFrete(),
                    proporcaoItens);

            double valorDescontoProporcional = calcularValorDescontoProporcional(valorDescontoTratado,
                    proporcaoItens);

            double valorAcrescimoProporcional = calcularValorAcrescimoProporcional(valorAcrescimoTratado,
                    proporcaoItens);

            double valorAPagar = calcularValorAPagar(totalPedido, valorEntregaProporcional,
                    valorDescontoProporcional, valorAcrescimoProporcional);

            String valorTratado = trataValorDecimal(valorAPagar);

            List<ItemPedido> itensPedidoDTO = BuilderClassesHelper
                    .criarItensPedidoDTO(pedido.getItensPedido());

            PagamentoDetalhesDTO detalhesItemSolicitado = BuilderClassesHelper
                    .criarDetalhesItemSolicitado(pedido.getNomeSolicitante(), itensPedidoDTO, valorTratado);

            String linkMetodoPagamento = vericaMetodoPagamento(carrinho.getTipoPagamento(), detalhesItemSolicitado);

            detalhesItemSolicitado.setUrlPix(linkMetodoPagamento);

            detalhesPagamento.add(detalhesItemSolicitado);

        });

        return detalhesPagamento;
    }

    private String vericaMetodoPagamento(TipoPagamento tipoPagamento,
                                         PagamentoDetalhesDTO pagamentoDetalhesDTO) {
        switch (tipoPagamento) {
            case PIX:
                return solicitarGeracaoDePix(pagamentoDetalhesDTO);
//            case BOLETO:
//                return solicitarGeraoDeBoleto(pagamentoDetalhesDTO);
//            case CREDITO:
//                return solicitarPagamentoCredito(pagamentoDetalhesDTO);
            default:
                return solicitarGeracaoDePix(pagamentoDetalhesDTO);
        }
    }

    private String solicitarGeracaoDePix(PagamentoDetalhesDTO pagamentoDetalhesDTO) {

        PixChargeDTO pixChargeInfo = PixChargeDTO.builder()
                .chave(chavePix)
                .nomePagador(pagamentoDetalhesDTO.getNomeSolicitante())
                .valor(pagamentoDetalhesDTO.getValorFinalParaPagar())
                .build();

        return pixService.pixCriarCobranca(pixChargeInfo);
    }

    private double calculaValorAcrescimo(double valorAcrescimo, double totalCompraOriginal,
                                         TipoAcrescimo tipoAcrescimo) {

        double valorTratado = trataValorAcrescimo(valorAcrescimo, tipoAcrescimo);

        if (tipoAcrescimo == TipoAcrescimo.PORCENTAGEM) {
            return totalCompraOriginal * valorTratado;
        } else {
            return valorTratado;
        }
    }

    private double calculaValorDesconto(double valorDesconto, double totalCompraOriginal,
                                        TipoDesconto tipoDesconto) {

        double valorTratado = trataValorDesconto(valorDesconto, tipoDesconto);

        if (tipoDesconto == TipoDesconto.PORCENTAGEM) {
            return totalCompraOriginal * valorTratado;
        } else {
            return valorTratado;
        }
    }

    private double trataValorAcrescimo(double valorAcrescimo, TipoAcrescimo tipoAcrescimo) {
        if (tipoAcrescimo == TipoAcrescimo.PORCENTAGEM) {
            return valorAcrescimo / 100;
        } else {
            return valorAcrescimo;
        }
    }

    private double trataValorDesconto(double valorDesconto, TipoDesconto tipoDesconto) {
        if (tipoDesconto == TipoDesconto.PORCENTAGEM) {
            return valorDesconto / 100;
        } else {
            return valorDesconto;
        }
    }

    private String trataValorDecimal(double valor) {
        BigDecimal valorBigDecimal = BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_EVEN);
        DecimalFormat df = new DecimalFormat("#.00");

        return df.format(valorBigDecimal);
    }

    private double calcularTotalCompra(List<Pedido> pedidos) {
        return pedidos.stream()
                .mapToDouble(pedido -> calcularTotalPedido(pedido.getItensPedido()))
                .sum();
    }

    private double calcularTotalPedido(List<ItemPedido> itensPedido) {
        return itensPedido.stream()
                .mapToDouble(ItemPedido::getValorItem)
                .sum();
    }

    private double calcularProporcao(double valor, double total) {
        return valor / total;
    }

    private double calcularValorAcrescimoProporcional(double valorAcrescimo, double proporcaoItens) {
        return valorAcrescimo * proporcaoItens;
    }


    private double calcularValorEntregaProporcional(double valorEntrega, double proporcaoItens) {
        return valorEntrega * proporcaoItens;
    }

    private double calcularValorDescontoProporcional(double desconto, double proporcaoItens) {
        return desconto * proporcaoItens;
    }

    private double calcularValorAPagar(double totalPedido, double valorEntregaProporcional,
                                       double valorDescontoProporcional, double valorAcrescimoProporcional) {
        return totalPedido + (valorEntregaProporcional - valorDescontoProporcional + valorAcrescimoProporcional);
    }
}
