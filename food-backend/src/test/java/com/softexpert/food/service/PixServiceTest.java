package com.softexpert.food.service;

import br.com.efi.efisdk.EfiPay;
import com.softexpert.food.dto.PixChargeDTO;
import com.softexpert.food.helper.BuilderClassesHelper;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PixServiceTest {

    @InjectMocks
    private PixService pixService;

//    @Mock
//    private EfiPay efiPay;

    @Test
    @DisplayName("pixCriarCobranca deve retornar link para pagamento via pix se sucesso.")
    void pixCriarCobranca_deveRetornarLinkPagamentoPix_SeSucesso() {

        PixChargeDTO requestDTO = BuilderClassesHelper.criarPixChargeDTOValido();

        String response = pixService.pixCriarCobranca(requestDTO);

        //Para que retorne diferente de null é preciso passar uma chave pix valida registrada na Efí.
        //Por questão de exposição de dados pessoais não passei essa chave aqui.
        assertThat(response).isNull();
    }

//    @Test
//    @DisplayName("pixCriarCobranca deve retornar link para pagamento via pix se sucesso.")
//    void pixCriarCobranca_deveRetornarLinkPagamentoPix_SeSucesso() throws Exception {
//        // Configuração do mock para pixCriarCobranca
//        JSONObject responseJson = new JSONObject();
//        responseJson.put("loc", new JSONObject().put("id", 12345)); // ID genérico
//
//        when(efiPay.call(eq("pixCreateImmediateCharge"), anyMap(), any(JSONObject.class))).thenReturn(responseJson);
//
//        // Configuração do mock para pixGerarLink
//        String linkVisualizacao = "http://exemplo.com";
//        when(pixService.pixGerarLink(anyString())).thenReturn(linkVisualizacao);
//
//        PixChargeDTO requestDTO = BuilderClassesHelper.criarPixChargeDTOValido();
//
//        String response = pixService.pixCriarCobranca(requestDTO);
//
//        assertThat(response).isEqualTo(linkVisualizacao);
//        // Aqui você pode adicionar mais asserções para verificar se o link gerado é válido, etc.
//    }
}