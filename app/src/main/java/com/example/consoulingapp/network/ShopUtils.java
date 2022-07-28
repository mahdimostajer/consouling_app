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

    public String getCourses() {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Client.BASE_URL + "/consultation/courses/").newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .headers(Client.getInstance().headers)
                .build();


        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 401) {
                Log.d("not authorized", "gholam");
                return null;
            }
            String res = response.body().string();
            Log.d("response", res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String buy(int id) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Client.BASE_URL + "/consultation/courses/" + String.valueOf(id) + "/purchase/").newBuilder();
        String url = urlBuilder.build().toString();
        RequestBody requestBody = RequestBody.create(null, new byte[0]);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .headers(Client.getInstance().headers)
                .build();


        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 201) {
                return "success";
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
