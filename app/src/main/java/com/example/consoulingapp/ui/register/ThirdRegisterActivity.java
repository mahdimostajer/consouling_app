package com.example.consoulingapp.ui.register;

import static com.example.consoulingapp.Utilities.getTextInputLayoutValue;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.consoulingapp.databinding.ActivityRegisterBinding;
import com.example.consoulingapp.databinding.ActivityThirdRegisterBinding;
import com.example.consoulingapp.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;

public class ThirdRegisterActivity extends AppCompatActivity {



    TextInputLayout phoneInput;
    TextInputLayout creditCardInput;
    DatePicker datePicker;

    String username;
    String password;
    String type;
    String gender;
    String firstName;
    String lastName;
    String province;
    String city;
    String phone;
    String creditCard;
    String date;


    private ActivityThirdRegisterBinding binding;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityThirdRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent prevIntent = getIntent();
        username = prevIntent.getStringExtra(RegisterActivity.USERNAME);
        password = prevIntent.getStringExtra(RegisterActivity.PASSWORD);
        type = prevIntent.getStringExtra(RegisterActivity.TYPE);
        gender = prevIntent.getStringExtra(SecondRegisterActivity.GENDER);
        firstName = prevIntent.getStringExtra(SecondRegisterActivity.FIRSTNAME);
        lastName = prevIntent.getStringExtra(SecondRegisterActivity.LASTNAME);
        province = prevIntent.getStringExtra(SecondRegisterActivity.PROVINCE);
        city = prevIntent.getStringExtra(SecondRegisterActivity.CITY);

        phoneInput = binding.registerPhoneNumber;
        creditCardInput = binding.registerCreditCardNo;
        datePicker = binding.registerBirthday;

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        registerViewModel.registerResponse.observe(this, registerResponse -> {
            if (registerResponse != null) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void register(View view) {
        date = datePicker.toString();
        creditCard = getTextInputLayoutValue(creditCardInput);
        phone = getTextInputLayoutValue(phoneInput);

        registerViewModel.register(
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
}