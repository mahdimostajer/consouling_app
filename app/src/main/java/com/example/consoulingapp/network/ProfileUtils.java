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
    private final OkHttpClient client;


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
            String parent_phone_number
                                ){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Client.BASE_URL + "/account/profile/").newBuilder();

        FormBody.Builder builder = new FormBody.Builder();

        addNotNull(builder,"grade",grade);
        addNotNull(builder,"state",state);
        addNotNull(builder,"field",field);
        addNotNull(builder,"school_name",school_name);
        addNotNull(builder,"school_type",school_type);
        addNotNull(builder,"average_level",average_level);
        addNotNull(builder,"extra_curricular_exam",extra_curricular_exam);
        addNotNull(builder,"latest_grade",latest_grade);
        addNotNull(builder,"description",description);
        addNotNull(builder,"parent_career",parent_career);
        addNotNull(builder,"parent_phone_number",parent_phone_number);

        RequestBody requestBody = builder.build();
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
    private void addNotNull(FormBody.Builder builder, String name, String value){
        if(value != null) builder.add(name, value);

    }
}
