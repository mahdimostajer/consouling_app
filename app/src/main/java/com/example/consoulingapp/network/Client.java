package com.example.consoulingapp.network;

import com.example.consoulingapp.models.LoginResp;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.OkHttpClient;

public class Client {
    private static Client single_instance = null;
    public OkHttpClient client;

    public static final String BASE_URL = "https://0089-51-89-200-156.eu.ngrok.io";
    public Headers headers;


    private Client() {
        client = new OkHttpClient();
    }

    public static Client getInstance() {
        if (single_instance == null)
            single_instance = new Client();

        return single_instance;
    }

    public void setLoginResp(LoginResp loginResp)
    {
        Map<String,String> map = new HashMap<String,String>();
        map.put("Authorization","JWT " +loginResp.access);
//        map.put("refresh",loginResp.refresh);
        this.headers = Headers.of(map);
    }
}