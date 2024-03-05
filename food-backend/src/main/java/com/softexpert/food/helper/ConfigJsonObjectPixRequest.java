package com.softexpert.food.helper;

import com.softexpert.food.pix.Credentials;
import org.json.JSONObject;

public class ConfigJsonObjectPixRequest {

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
