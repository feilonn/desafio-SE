package com.softexpert.food.service;

import com.softexpert.food.domain.exception.BadRequestException;
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

    public List<PagamentoDetalhesDTO> processarPedido(CarrinhoDTO carrinho) {

        List<PagamentoDetalhesDTO> detalhesPagamento = new ArrayList<>();

        BigDecimal totalCompraSemFreteEDescontos = calcularTotalCompra(carrinho.getPedidos());

        BigDecimal valorAcrescimoTratado = calculaValorAcrescimo(carrinho.getAcrescimo(), totalCompraSemFreteEDescontos,
                carrinho.getTipoAcrescimo());

        BigDecimal valorDescontoTratado = calculaValorDesconto(carrinho.getDesconto(), totalCompraSemFreteEDescontos,
                carrinho.getTipoDesconto());

        carrinho.getPedidos().forEach(pedido -> {

            BigDecimal totalPedido = calcularTotalPedido(pedido.getItensPedido());

            BigDecimal proporcaoItens = calcularProporcao(totalPedido, totalCompraSemFreteEDescontos);

            BigDecimal valorEntregaProporcional = calcularValorProporcional(carrinho.getValorFrete(),
                    proporcaoItens);

            BigDecimal valorDescontoProporcional = calcularValorProporcional(valorDescontoTratado,
                    proporcaoItens);

            BigDecimal valorAcrescimoProporcional = calcularValorProporcional(valorAcrescimoTratado,
                    proporcaoItens);

            BigDecimal valorAPagar = calcularValorFinalAPagar(totalPedido, valorEntregaProporcional,
                    valorDescontoProporcional, valorAcrescimoProporcional);

            String valorTratado = trataValorDecimal(valorAPagar);

            List<ItemPedido> itensPedidoDTO = BuilderClassesHelper
                    .criarItensPedidoDTO(pedido.getItensPedido());

            PagamentoDetalhesDTO detalhesItemSolicitado = criarDetalhesItemSolicitado(pedido,
                    valorTratado, itensPedidoDTO, carrinho.getTipoPagamento());

            detalhesPagamento.add(detalhesItemSolicitado);

        });

        return detalhesPagamento;
    }

    private String solicitarGeracaoDePix(PagamentoDetalhesDTO pagamentoDetalhesDTO) {

        PixChargeDTO pixChargeInfo = PixChargeDTO.builder()
                .chave(chavePix)
                .nomePagador(pagamentoDetalhesDTO.getNomeSolicitante())
                .valor(pagamentoDetalhesDTO.getValorFinalParaPagar())
                .build();

        return pixService.pixCriarCobranca(pixChargeInfo);
    }

    private BigDecimal calculaValorAcrescimo(BigDecimal valorAcrescimo, BigDecimal totalCompraOriginal,
                                         TipoAcrescimo tipoAcrescimo) {

        BigDecimal valorTratado = trataValorAcrescimo(valorAcrescimo, tipoAcrescimo);

        if (tipoAcrescimo == TipoAcrescimo.PORCENTAGEM) {
            return totalCompraOriginal.multiply(valorTratado);
        } else {
            return valorTratado;
        }
    }

    private BigDecimal calculaValorDesconto(BigDecimal valorDesconto, BigDecimal totalCompraOriginal,
                                        TipoDesconto tipoDesconto) {

        BigDecimal valorTratado = trataValorDesconto(valorDesconto, tipoDesconto);

        if (tipoDesconto == TipoDesconto.PORCENTAGEM) {
            return totalCompraOriginal.multiply(valorTratado);
        } else {
            return valorTratado;
        }
    }

    private BigDecimal trataValorAcrescimo(BigDecimal valorAcrescimo, TipoAcrescimo tipoAcrescimo) {
        if (tipoAcrescimo == TipoAcrescimo.PORCENTAGEM) {
            return valorAcrescimo.divide(new BigDecimal("100"));
        } else {
            return valorAcrescimo;
        }
    }

    private BigDecimal trataValorDesconto(BigDecimal valorDesconto, TipoDesconto tipoDesconto) {
        if (tipoDesconto == TipoDesconto.PORCENTAGEM) {
            return valorDesconto.divide(new BigDecimal("100"));
        } else {
            return valorDesconto;
        }
    }

    private String trataValorDecimal(BigDecimal valor) {
        BigDecimal valorBigDecimal = valor.setScale(2, RoundingMode.HALF_EVEN);
        DecimalFormat df = new DecimalFormat("#.00");

        return df.format(valorBigDecimal);
    }

    private BigDecimal calcularTotalCompra(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> calcularTotalPedido(pedido.getItensPedido()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calcularTotalPedido(List<ItemPedido> itensPedido) {
        return itensPedido.stream()
                .map(ItemPedido::getValorItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calcularProporcao(BigDecimal valor, BigDecimal total) {
        int escala = 2;
        RoundingMode modoArredondamento = RoundingMode.HALF_EVEN;

        return valor.divide(total, escala, modoArredondamento);
    }

    private BigDecimal calcularValorProporcional(BigDecimal valorOriginal, BigDecimal proporcaoItens) {
        return valorOriginal.multiply(proporcaoItens);
    }

    private BigDecimal calcularValorFinalAPagar(BigDecimal totalPedido, BigDecimal valorEntregaProporcional,
                                            BigDecimal valorDescontoProporcional,
                                            BigDecimal valorAcrescimoProporcional) {

        BigDecimal resultado = totalPedido
                .add(valorEntregaProporcional)
                .subtract(valorDescontoProporcional)
                .add(valorAcrescimoProporcional);

        // Retornando o resultado como double, caso seja necessário
        return resultado;
    }

    private PagamentoDetalhesDTO criarDetalhesItemSolicitado(Pedido pedido, String valorTratado,
                                                             List<ItemPedido> itensPedidoDTO,
                                                             TipoPagamento tipoPagamento) {

        PagamentoDetalhesDTO detalhesItemSolicitado = BuilderClassesHelper
                .criarDetalhesItemSolicitado(pedido.getNomeSolicitante(), itensPedidoDTO, valorTratado);

        String linkMetodoPagamento = verificarMetodoPagamento(tipoPagamento, detalhesItemSolicitado);

        detalhesItemSolicitado.setUrlPix(linkMetodoPagamento);

        return detalhesItemSolicitado;
    }

    private String verificarMetodoPagamento(TipoPagamento tipoPagamento,
                                            PagamentoDetalhesDTO pagamentoDetalhesDTO) {
        switch (tipoPagamento) {
            case PIX:
                return solicitarGeracaoDePix(pagamentoDetalhesDTO);
            case BOLETO:
                throw new BadRequestException("Ocorreu um erro no processamento." +
                        " O método de pagamento por boleto está indisponível no momento.");

                //Novas formas de pagamento
//            case CREDITO:
//                return solicitarPagamentoCredito(pagamentoDetalhesDTO);
            default:
                return solicitarGeracaoDePix(pagamentoDetalhesDTO);
        }
    }
}
