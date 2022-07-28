package com.example.consoulingapp.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShopUtils {
    private OkHttpClient client;


    public ShopUtils() {
        client = Client.getInstance().client;
    }

    public String getCourses(String token){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Client.BASE_URL + "consultation/courses/").newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();


        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 401) {
                Log.d("not authorized", "gholam");
                return null;
            }
            String res = response.body().string();
            Log.d("response",res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
