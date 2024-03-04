package com.softexpert.food.dto;

import com.softexpert.food.domain.model.ItemPedido;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PagamentoDetalhesDTO {

    private List<ItemPedido> itensPedido;
    private String nomeSolicitante;
    private String valorFinalParaPagar;
    private String urlPix;

}
