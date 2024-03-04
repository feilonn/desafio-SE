package com.softexpert.food.domain.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
public class ItemPedido {

    private String tituloItem;

    @PositiveOrZero(message = "O valor do item não pode ser negativo.")
    private double valorItem;
}
