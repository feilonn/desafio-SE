package com.softexpert.food.controller;

import com.softexpert.food.dto.CarrinhoDTO;
import com.softexpert.food.dto.PagamentoDetalhesDTO;
import com.softexpert.food.helper.BuilderClassesHelper;
import com.softexpert.food.service.PagamentoService;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagamentoControllerTest {

    @InjectMocks
    private PagamentoController pagamentoController;

    @Mock
    private PagamentoService pagamentoService;

//    @Test
//    @SneakyThrows
//    void calcularPagamento() {
//
//        CarrinhoDTO dto = CarrinhoDTO.builder().build();
//
//        pagamentoController.calcularPagamento(dto);
//
//    }

    @Test
    @DisplayName("calcularPagamento deve retornar response entity ok se sucesso")
    void calcularPagamento_deveRetornarResponseEntityDto_SeSucesso() {

        CarrinhoDTO dtoRequest = CarrinhoDTO.builder().build();

        List<PagamentoDetalhesDTO> list = BuilderClassesHelper.criarListDetalhesItemSolicitado();

        when(pagamentoService.processarPedido(dtoRequest)).thenReturn(list);

        ResponseEntity<List<PagamentoDetalhesDTO>> responseEntity = pagamentoController.processarPedido(dtoRequest);

        assertThat(responseEntity).isNotNull();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(responseEntity.getBody()).isNotNull();

        assertThat(responseEntity.getBody()).isEqualTo(list);
    }
}