package com.softexpert.food.dto;

import com.softexpert.food.domain.model.Pedido;
import com.softexpert.food.enums.TipoAcrescimo;
import com.softexpert.food.enums.TipoDesconto;
import com.softexpert.food.enums.TipoPagamento;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CarrinhoDTO {

    @NotNull
    @NotEmpty
    @Valid
    private List<Pedido> pedidos;

    @PositiveOrZero(message = "O valor do frete não pode ser negativo")
    private BigDecimal valorFrete;

    @PositiveOrZero(message = "O valor do desconto não pode ser negativo")
    private BigDecimal desconto;

    @PositiveOrZero(message = "O valor do acrescimo não pode ser negativo")
    private BigDecimal acrescimo;

    private TipoDesconto tipoDesconto;

    private TipoAcrescimo tipoAcrescimo;

    @NotNull(message = "O Tipo de Pagamento deve ser informado!")
    private TipoPagamento tipoPagamento;
}
