package com.softexpert.food.controller;

import com.softexpert.food.dto.CarrinhoDTO;
import com.softexpert.food.dto.PagamentoDetalhesDTO;
import com.softexpert.food.service.PagamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api(value = "API REST SoftExpert")
@RestController
@RequestMapping("api/v1/food")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping("processarPedido")
    @ApiOperation(value = "Retorna uma lista de pedidos, com seu valor inicial, final e link para pagamento.")
    public ResponseEntity<List<PagamentoDetalhesDTO>> processarPedido(@RequestBody @Valid CarrinhoDTO carrinhoDTO) {

        List<PagamentoDetalhesDTO> detalhesPagamento = pagamentoService.processarPedido(carrinhoDTO);
        return ResponseEntity.ok(detalhesPagamento);
    }

}
