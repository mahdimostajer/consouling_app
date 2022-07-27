package com.example.consoulingapp.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Client.BASE_URL + "/core/register/").newBuilder();
        RequestBody requestBody = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .add("password_confirm",password)
                .add("first_name",firstName)
                .add("last_name",lastName)
                .add("gender",gender)
                .add("birth_date",date)
                .add("credit_card_no",creditCard)
                .add("city",city)
                .add("province",province)
                .add("phone_number",phone)
                .add("account_type",type)
                .build();
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
