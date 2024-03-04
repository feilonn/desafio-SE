package com.softexpert.food.controller;

import com.softexpert.food.dto.PixChargeDTO;
import com.softexpert.food.dto.CarrinhoDTO;
import com.softexpert.food.dto.PagamentoDetalhesDTO;
import com.softexpert.food.service.PagamentoService;
import com.softexpert.food.service.PixService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("food")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final PixService pixService;

    @PostMapping("calculaValoresPedido")
    public ResponseEntity<List<PagamentoDetalhesDTO>> calcularPagamento(@RequestBody @Valid CarrinhoDTO carrinhoDTO) {

        List<PagamentoDetalhesDTO> detalhesPagamento = pagamentoService.calcularPagamento(carrinhoDTO);
        return ResponseEntity.ok(detalhesPagamento);
    }

    @GetMapping("pix")
    public ResponseEntity<String> createPixEVP() {

        JSONObject response = pixService.pixCriarChaveAleatoria();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.toString());
    }

    @PostMapping("pix/charge")
    public ResponseEntity<String> createPixCharge(@RequestBody PixChargeDTO pixCharge) {

        String response = pixService.pixCriarCobranca(pixCharge);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

}
