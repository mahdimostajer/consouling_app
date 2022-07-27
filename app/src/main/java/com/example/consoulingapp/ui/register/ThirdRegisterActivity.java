package com.example.consoulingapp.ui.register;

import static com.example.consoulingapp.Utilities.getTextInputLayoutValue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.consoulingapp.databinding.ActivityThirdRegisterBinding;
import com.example.consoulingapp.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
        if (!validatePhoneNumber() || !validateCreditCard()) return;
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.format(calendar.getTime());
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

    private boolean validatePhoneNumber(){
        String value = getTextInputLayoutValue(phoneInput);
        if(value.isEmpty()){
            phoneInput.setError("شماره تلفن نمیتواند خالی باشد");
            return false;
        }
        phoneInput.setError(null);
        phoneInput.setErrorEnabled(false);
        return true;
    }

    private boolean validateCreditCard(){
        String value = getTextInputLayoutValue(creditCardInput);
        if(value.isEmpty()){
            creditCardInput.setError("شماره کارت نمیتواند خالی باشد");
            return false;
        }
        creditCardInput.setError(null);
        creditCardInput.setErrorEnabled(false);
        return true;
    }
}