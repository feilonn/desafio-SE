package com.softexpert.food.service;

import com.softexpert.food.domain.exception.BadRequestException;
import com.softexpert.food.dto.CarrinhoDTO;
import com.softexpert.food.dto.PagamentoDetalhesDTO;
import com.softexpert.food.dto.PixChargeDTO;
import com.softexpert.food.enums.TipoAcrescimo;
import com.softexpert.food.enums.TipoDesconto;
import com.softexpert.food.enums.TipoPagamento;
import com.softexpert.food.helper.BuilderClassesHelper;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {

    @InjectMocks
    private PagamentoService pagamentoService;

    @Mock
    PixService pixService;

    @Test
    @DisplayName("processarPedido deve retornar detalhes pagamento se sucesso com desconto inteiro")
    void processarPedido_deveRetornarList_SeSucesso_DescontoEAcrescimoInteiro() {

        ReflectionTestUtils.setField(pagamentoService, "chavePix", "chave_pix");

        CarrinhoDTO dtoRequest = BuilderClassesHelper.criarCarrinhoDTOValido();
        PixChargeDTO pixChargeDTO = BuilderClassesHelper.criarPixChargeDTOValido();
        String responseUrlPix = "pix.com";

        when(pixService.pixCriarCobranca(pixChargeDTO)).thenReturn(responseUrlPix);

        List<PagamentoDetalhesDTO> response = pagamentoService.processarPedido(dtoRequest);

        assertThat(response).isNotNull();
        assertThat(response.get(0).getNomeSolicitante()).isEqualTo(pixChargeDTO.getNomePagador());
        assertThat(response.get(0).getValorFinalParaPagar()).isEqualTo(pixChargeDTO.getValor());
    }

    @Test
    @DisplayName("processarPedido deve retornar detalhes pagamento se sucesso com desconto porcentagem")
    void processarPedido_deveRetornarList_SeSucesso_DescontoPorcentagem() {

        ReflectionTestUtils.setField(pagamentoService, "chavePix", "chave_pix");

        CarrinhoDTO dtoRequest = BuilderClassesHelper.criarCarrinhoDTOValido();
        dtoRequest.setTipoDesconto(TipoDesconto.PORCENTAGEM);
        PixChargeDTO pixChargeDTO = BuilderClassesHelper.criarPixChargeDTOValido();
        String responseUrlPix = "pix.com";

        when(pixService.pixCriarCobranca(pixChargeDTO)).thenReturn(responseUrlPix);

        List<PagamentoDetalhesDTO> response = pagamentoService.processarPedido(dtoRequest);

        assertThat(response).isNotNull();
        assertThat(response.get(0).getNomeSolicitante()).isEqualTo(pixChargeDTO.getNomePagador());
        assertThat(response.get(0).getValorFinalParaPagar()).isEqualTo(pixChargeDTO.getValor());
    }

    @Test
    @DisplayName("processarPedido deve retornar detalhes pagamento se sucesso com acrescimo porcentagem")
    void processarPedido_deveRetornarList_SeSucesso_AcrescimoPorcentagem() {

        ReflectionTestUtils.setField(pagamentoService, "chavePix", "chave_pix");

        CarrinhoDTO dtoRequest = BuilderClassesHelper.criarCarrinhoDTOValido();
        dtoRequest.setTipoAcrescimo(TipoAcrescimo.PORCENTAGEM);
        PixChargeDTO pixChargeDTO = BuilderClassesHelper.criarPixChargeDTOValido();
        String responseUrlPix = "pix.com";

        when(pixService.pixCriarCobranca(pixChargeDTO)).thenReturn(responseUrlPix);

        List<PagamentoDetalhesDTO> response = pagamentoService.processarPedido(dtoRequest);

        assertThat(response).isNotNull();
        assertThat(response.get(0).getNomeSolicitante()).isEqualTo(pixChargeDTO.getNomePagador());
        assertThat(response.get(0).getValorFinalParaPagar()).isEqualTo(pixChargeDTO.getValor());
    }

    @Test
    @DisplayName("processarPedido deve retornar detalhes pagamento via pix se metodo pagamento nao especificado.")
    void processarPedido_deveRetornarList_SeSucesso_MetodoPagamentoNaoEspecificado() {

        ReflectionTestUtils.setField(pagamentoService, "chavePix", "chave_pix");

        CarrinhoDTO dtoRequest = BuilderClassesHelper.criarCarrinhoDTOValido();
        dtoRequest.setTipoPagamento(TipoPagamento.DEBITO);
        dtoRequest.setTipoAcrescimo(TipoAcrescimo.PORCENTAGEM);
        PixChargeDTO pixChargeDTO = BuilderClassesHelper.criarPixChargeDTOValido();
        String responseUrlPix = "pix.com";

        when(pixService.pixCriarCobranca(pixChargeDTO)).thenReturn(responseUrlPix);

        List<PagamentoDetalhesDTO> response = pagamentoService.processarPedido(dtoRequest);

        assertThat(response).isNotNull();
        assertThat(response.get(0).getNomeSolicitante()).isEqualTo(pixChargeDTO.getNomePagador());
        assertThat(response.get(0).getValorFinalParaPagar()).isEqualTo(pixChargeDTO.getValor());
    }

    @Test
    @DisplayName("processarPedido deve lançar exception se o método de pagamento não estiver disponível.")
    void processarPedido_deveLancarException_SeMetodoPagamentoNaoDisponivel() {

        ReflectionTestUtils.setField(pagamentoService, "chavePix", "chave_pix");

        CarrinhoDTO dtoRequest = BuilderClassesHelper.criarCarrinhoDTOValido();
        dtoRequest.setTipoPagamento(TipoPagamento.BOLETO);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            pagamentoService.processarPedido(dtoRequest);
        });

        assertThat(exception.getMessage()).isEqualTo("Ocorreu um erro no processamento." +
                " O método de pagamento por boleto está indisponível no momento.");
    }


}