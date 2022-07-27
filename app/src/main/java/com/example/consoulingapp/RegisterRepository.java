package com.example.consoulingapp;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.consoulingapp.models.RegisterResponse;
import com.example.consoulingapp.models.User;
import com.example.consoulingapp.network.RegisterUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class RegisterRepository {
    public MutableLiveData<RegisterResponse> registerResponse = new MutableLiveData<>();
    private final Application application;

    public RegisterRepository(Application application) {
        this.application = application;
    }

    public void register(
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
        new registerUser(registerResponse).execute(
                username,
                password,
                type,
                gender,
                firstName,
                lastName,
                province,
                city,
                phone,
                creditCard,
                date
        );
    }

    public class registerUser extends AsyncTask<String, Void, String>{
        MutableLiveData<RegisterResponse> registerResponse;

        public registerUser(MutableLiveData<RegisterResponse> registerResponse) {
            this.registerResponse = registerResponse;
        }

        @Override
        protected String doInBackground(String... strings) {
            RegisterUtils registerUtils = new RegisterUtils();
            return registerUtils.register(
                    strings[0],
                    strings[1],
                    strings[2],
                    strings[3],
                    strings[4],
                    strings[5],
                    strings[6],
                    strings[7],
                    strings[8],
                    strings[9],
                    strings[10]
                    );
        }

        @Override
        protected void onPostExecute(String s) {
            if (s == null) {
                Toast.makeText(application, "اطلاعات وارد شده صحیح نیست", Toast.LENGTH_LONG).show();
                return;
            }
            super.onPostExecute(s);
            Gson gson = new Gson();
            Type mapType = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> map = new Gson().fromJson(s, mapType);
            if (map.size() == 1){
                Toast.makeText(application,(map.values().toArray()[0]).toString(),Toast.LENGTH_LONG).show();
            }
            else{
                RegisterResponse resp = gson.fromJson(s, RegisterResponse.class);
                registerResponse.setValue(resp);
            }
        }
    }
}