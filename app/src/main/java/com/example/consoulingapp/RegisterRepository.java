package com.example.consoulingapp;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.example.consoulingapp.models.RegisterResponse;
import com.example.consoulingapp.models.User;
import com.example.consoulingapp.network.RegisterUtils;
import com.google.gson.Gson;

public class RegisterRepository {
    public MutableLiveData<RegisterResponse> registerResponse = new MutableLiveData<>();


    public RegisterRepository() {
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
            super.onPostExecute(s);
            Gson gson = new Gson();
//            Resp resp = gson.fromJson(s, Resp.class);
            RegisterResponse newRegisterResponse = new RegisterResponse("ghjtffyj", "123","akbari","male","armin","delgosar","12324124124","2022-07-22");
            registerResponse.setValue(newRegisterResponse);
        }
    }
}