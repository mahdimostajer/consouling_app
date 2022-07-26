package com.example.consoulingapp.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginUtils {
    private OkHttpClient client;

    // TODO: headers

    public LoginUtils() {
        client = Client.getInstance().client;
    }

    public String authenticate(String username, String password){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Client.BASE_URL + "/core/login/").newBuilder();
        RequestBody requestBody = new FormBody.Builder().add("username",username).add("password",password).build();
        // TODO: set params
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            Log.d("response",res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
