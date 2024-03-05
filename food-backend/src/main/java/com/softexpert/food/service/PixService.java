package com.softexpert.food.service;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import com.softexpert.food.dto.PixChargeDTO;
import com.softexpert.food.helper.ConfigJsonObjectPixRequest;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class PixService {

    public String pixCriarCobranca(PixChargeDTO pixCharge) {

        JSONObject options = ConfigJsonObjectPixRequest.configuringJsonObject();

        JSONObject body = new JSONObject();
        body.put("calendario", new JSONObject().put("expiracao", 3600));
        body.put("devedor", new JSONObject().put("cpf", "12345678909").put("nome", pixCharge.getNomePagador()));
        body.put("valor", new JSONObject().put("original", pixCharge.getValor()));
        body.put("chave", pixCharge.getChave());

        try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("pixCreateImmediateCharge", new HashMap<String,String>(), body);

            int idFromJson= response.getJSONObject("loc").getInt("id");

            return pixGerarLink(String.valueOf(idFromJson));

        }catch (EfiPayException e){
            log.error(e.getError());
            log.error(e.getErrorDescription());
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }

    private String pixGerarLink(String id){
        String linkVisualizacao = "";

        JSONObject options = ConfigJsonObjectPixRequest.configuringJsonObject();

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        try {
            EfiPay efi= new EfiPay(options);
            Map<String, Object> response = efi.call("pixGenerateQRCode", params, new HashMap<String, Object>());

            linkVisualizacao = (String) response.get("linkVisualizacao");

        }catch (EfiPayException e){
            log.error(e.getError());
            log.error(e.getErrorDescription());
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }

        return linkVisualizacao;
    }

}
