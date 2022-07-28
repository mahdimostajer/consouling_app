package com.example.consoulingapp.network;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeUtils {
    private OkHttpClient client;

    // TODO: headers

    public HomeUtils() {
        client = Client.getInstance().client;
    }

    public String getProfile(){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Client.BASE_URL + "/account/profile/").newBuilder();

        String url = urlBuilder.build().toString();


        Request request = new Request.Builder()
                .url(url)
                .headers(Client.getInstance().headers)
                .get()
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
    public String getDashboard(){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Client.BASE_URL + "/account/student/dashboard/").newBuilder();

        String url = urlBuilder.build().toString();


        Request request = new Request.Builder()
                .url(url)
                .headers(Client.getInstance().headers)
                .get()
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
