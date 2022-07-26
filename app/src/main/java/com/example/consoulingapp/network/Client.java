package com.example.consoulingapp.network;

import okhttp3.OkHttpClient;

public class Client {
    private static Client single_instance = null;
    public OkHttpClient client;

    public static final String BASE_URL = "https://1ac8-51-89-200-156.eu.ngrok.io";


    private Client() {
        client = new OkHttpClient();
    }

    public static Client getInstance() {
        if (single_instance == null)
            single_instance = new Client();

        return single_instance;
    }

}