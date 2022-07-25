package com.example.consoulingapp.ui.register;

import static com.example.consoulingapp.Utilities.getTextInputLayoutValue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.consoulingapp.databinding.ActivitySecondRegisterBinding;
import com.google.android.material.textfield.TextInputLayout;

public class SecondRegisterActivity extends AppCompatActivity {


    RadioGroup gender;
    TextInputLayout firstName;
    TextInputLayout lastName;
    TextInputLayout province;
    TextInputLayout city;
    Intent prevIntent;

    public static String FIRSTNAME = "SecondRegisterActivityFirstName";
    public static String LASTNAME = "SecondRegisterActivityLastName";
    public static String PROVINCE = "SecondRegisterActivityProvince";
    public static String CITY = "SecondRegisterActivityCity";
    public static String GENDER = "SecondRegisterActivityGender";

    private ActivitySecondRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivitySecondRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prevIntent = getIntent();
        gender = binding.gender;
        firstName = binding.registerFirstname;
        lastName = binding.registerLastname;
        province = binding.registerProvince;
        city = binding.registerCity;


    }




    public void callNextRegisterScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), ThirdRegisterActivity.class);
        intent.putExtras(prevIntent.getExtras());
        intent.putExtra(SecondRegisterActivity.FIRSTNAME, getTextInputLayoutValue(firstName));
        intent.putExtra(SecondRegisterActivity.LASTNAME, getTextInputLayoutValue(lastName));
        intent.putExtra(SecondRegisterActivity.PROVINCE, getTextInputLayoutValue(province));
        intent.putExtra(SecondRegisterActivity.CITY, getTextInputLayoutValue(city));
        intent.putExtra(SecondRegisterActivity.GENDER, gender.getCheckedRadioButtonId());
        startActivity(intent);


    }


}