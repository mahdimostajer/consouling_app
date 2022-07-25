package com.example.consoulingapp.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterUtils {
    private OkHttpClient client;

    // TODO: headers

    public RegisterUtils() {
        client = Client.getInstance().client;
    }

    public String register(
            String username,
            String password,
            String type,
            String gender,
            String firstName,
            String lastName,
            String province,
            String city,
            String phone,
            String creditCard,
            String date
    ){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Client.BASE_URL).newBuilder();
        // TODO: set params
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
