package com.softexpert.food.domain.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
@Builder
public class Pedido {

    @Valid
    private List<ItemPedido> itensPedido;
    private String nomeSolicitante;
}
