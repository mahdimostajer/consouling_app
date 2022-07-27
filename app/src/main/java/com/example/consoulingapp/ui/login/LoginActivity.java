package com.example.consoulingapp.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consoulingapp.databinding.ActivityLoginBinding;
import com.example.consoulingapp.models.User;
import com.example.consoulingapp.ui.panel.PanelActivity;
import com.example.consoulingapp.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private SharedPreferences mPreferences;

    public final static String sharedPrefFile =
            "com.example.android.consouling_app";
    public final static String ACCESS_TOKEN = "access token";
    public final static String REFRESH_TOKEN = "refresh token";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        binding.registerTextview.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });


        binding.loginButton.setOnClickListener(view -> {
            String username = binding.usernameEdittext.getEditText().getText().toString();
            String password = binding.passwordEdittext.getEditText().getText().toString();
            if (username.length() == 0) {
                binding.usernameEdittext.setError("نام کاربری نمیتواند خالی باشد");
            } else {
                binding.usernameEdittext.setErrorEnabled(false);
            }
            if (password.length() == 0) {
                binding.passwordEdittext.setError("رمز عبور نمیتواند خالی باشد");
            }else {
                binding.passwordEdittext.setErrorEnabled(false);
            }
            if(username.length() != 0 && password.length() != 0){
                loginViewModel.authenticate(username, password);

            }

        });

        loginViewModel.user.observe(this, user -> {
            if (user != null) {
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putString(ACCESS_TOKEN, user.access);
                preferencesEditor.putString(REFRESH_TOKEN, user.refresh);
                preferencesEditor.apply();
                Intent intent = new Intent(getApplicationContext(), PanelActivity.class);
                startActivity(intent);
            }
        });
    }
}