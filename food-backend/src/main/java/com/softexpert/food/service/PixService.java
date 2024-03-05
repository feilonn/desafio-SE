package com.softexpert.food.service;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import com.softexpert.food.dto.PixChargeDTO;
import com.softexpert.food.helper.ConfigJsonObjectPixRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class PixService {

//    public JSONObject pixCriarChaveAleatoria() {
//
//        JSONObject options = ConfigJsonObjectPixRequest.configuringJsonObject();
//
//        try {
//            EfiPay efi = new EfiPay(options);
//            JSONObject response = efi.call("pixCreateEvp", new HashMap<String,String>(), new JSONObject());
//            System.out.println(response.toString());
//            return response;
//        }catch (EfiPayException e){
//            System.out.println(e.getError());
//            System.out.println(e.getErrorDescription());
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }

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
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return linkVisualizacao;
    }

}
