package com.example.consoulingapp.network;

import okhttp3.OkHttpClient;

public class ShopUtils {
    private OkHttpClient client;


    public ShopUtils() {
        client = Client.getInstance().client;
    }

    public String getCourses(String token){
        return null;
    }
}
