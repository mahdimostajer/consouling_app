package com.example.consoulingapp.network;

import android.util.Log;

import com.example.consoulingapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProfileUtils {
    private OkHttpClient client;


    public ProfileUtils() {
        client = Client.getInstance().client;
    }

    public String changeDetails(
            String grade,
            String state,
            String field,
            String school_name,
            String school_type,
            String average_level,
            String extra_curricular_exam,
            String latest_grade,
            String description,
            String parent_career,
            String parent_phone_number,
            String ranked_in_country,
            String ranked_in_area
                                ){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Client.BASE_URL + "/account/profile/").newBuilder();
        RequestBody requestBody = new FormBody.Builder()
                .add("grade",grade)
                .add("state",state)
                .add("field",field)
                .add("school_name",school_name)
                .add("school_type",school_type)
                .add("average_level",average_level)
                .add("extra_curricular_exam",extra_curricular_exam)
                .add("latest_grade",latest_grade)
                .add("description",description)
                .add("parent_career",parent_career)
                .add("parent_phone_number",parent_phone_number)
                .add("ranked_in_country",ranked_in_country)
                .add("ranked_in_area",ranked_in_area)
                .build();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .headers(Client.getInstance().headers)
                .patch(requestBody)
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
