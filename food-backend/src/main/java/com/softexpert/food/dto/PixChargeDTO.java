package com.softexpert.food.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PixChargeDTO {

    private String chave;
    private String valor;
    private String nomePagador;

}
