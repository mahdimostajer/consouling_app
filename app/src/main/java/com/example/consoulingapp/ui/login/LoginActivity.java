package com.example.consoulingapp.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consoulingapp.RegisterActivity;
import com.example.consoulingapp.databinding.ActivityLoginBinding;
import com.example.consoulingapp.models.User;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.registerTextview.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });


        binding.loginButton.setOnClickListener(view -> {
            String username = binding.usernameEdittext.getEditText().getText().toString();
            String password = binding.passwordEdittext.getEditText().getText().toString();
            if (username.length() == 0 || password.length() == 0) {
                Toast.makeText(this, "لطفا اطلاعات خود را وارد کنید", Toast.LENGTH_LONG).show();
            } else {
                loginViewModel.authenticate(username, password);
            }

        });

        loginViewModel.user.observe(this, user -> {
            if (user != null) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}