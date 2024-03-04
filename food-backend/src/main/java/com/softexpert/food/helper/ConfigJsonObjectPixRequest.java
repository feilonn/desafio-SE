package com.softexpert.food.helper;

import com.softexpert.food.pix.Credentials;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

public class ConfigJsonObjectPixRequest {

    @Value("${CLIENT_ID}")
    private String clientId;

    @Value("${CLIENT_SECRET}")
    private String clientSecret;

    public static JSONObject configuringJsonObject(){
        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        return options;
    }

}
