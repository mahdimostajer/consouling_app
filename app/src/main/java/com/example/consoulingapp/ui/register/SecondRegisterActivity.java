package com.example.consoulingapp.ui.register;

import static com.example.consoulingapp.Utilities.getTextInputLayoutValue;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.consoulingapp.R;
import com.example.consoulingapp.databinding.ActivitySecondRegisterBinding;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

public class SecondRegisterActivity extends AppCompatActivity {


    RadioGroup gender;
    TextInputLayout firstName;
    TextInputLayout lastName;
    MaterialAutoCompleteTextView provinceAutoCompleteTextView;
    MaterialAutoCompleteTextView cityAutoCompleteTextView;
    Intent prevIntent;

    public static String FIRSTNAME = "SecondRegisterActivityFirstName";
    public static String LASTNAME = "SecondRegisterActivityLastName";
    public static String PROVINCE = "SecondRegisterActivityProvince";
    public static String CITY = "SecondRegisterActivityCity";
    public static String GENDER = "SecondRegisterActivityGender";

    private String[] PROVINCES;
    private Map<String, String[]> CITIES;
    private String citiesJson;

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
        provinceAutoCompleteTextView = binding.registerProvince;
        cityAutoCompleteTextView = binding.registerCity;
        setAutoCompleteFields();
    }
    private void setAutoCompleteFields(){
        setCitiesJson();
        PROVINCES = (new String[]{"آذربایجان شرقی", "آذربایجان غربی", "اردبیل", "اصفهان", "البرز", "ایلام", "بوشهر", "تهران", "خراسان جنوبی",
                "خراسان رضوی", "خراسان شمالی", "خوزستان", "زنجان", "سمنان", "سیستان و بلوچستان", "فارس", "قزوین", "قم",
                "لرستان", "مازندران", "مرکزی", "هرمزگان", "همدان", "چهارمحال و بختیاری", "کردستان", "کرمان", "کرمانشاه",
                "کهگیلویه وبویراحمد", "گلستان", "گیلان", "یزد"});
        Type mapType = new TypeToken<Map<String, String[]>>(){}.getType();
        CITIES = new Gson().fromJson(citiesJson, mapType);

        ArrayAdapter arrayAdapter = new ArrayAdapter(SecondRegisterActivity.this, R.layout.drop_down_item, PROVINCES);
        binding.registerProvince.setAdapter(arrayAdapter);

        provinceAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                    String[] cities = Objects.requireNonNull(CITIES.getOrDefault(((TextView) view).getText().toString(),new String[]{}));
                    ArrayAdapter arrayAdapter = new ArrayAdapter(SecondRegisterActivity.this, R.layout.drop_down_item, cities);
                    binding.registerCity.setAdapter(arrayAdapter);
            }
        });



    }

    public void setCitiesJson() {
        String json = null;
        try {
            InputStream is = SecondRegisterActivity.this.getAssets().open("Cities.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        citiesJson = json;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void callNextRegisterScreen(View view) {
        int genderInt = gender.getCheckedRadioButtonId();
        String genderString;
        if(genderInt == findViewById(R.id.male).getId()){
            genderString = "male";
        }
        else if(genderInt == findViewById(R.id.female).getId()){
            genderString = "female";
        }
        else if(genderInt == findViewById(R.id.other).getId()){
            genderString = "other";
        }
        else{
            return;
        }
        Intent intent = new Intent(getApplicationContext(), ThirdRegisterActivity.class);
        intent.putExtras(prevIntent.getExtras());
        intent.putExtra(SecondRegisterActivity.FIRSTNAME, getTextInputLayoutValue(firstName));
        intent.putExtra(SecondRegisterActivity.LASTNAME, getTextInputLayoutValue(lastName));
        intent.putExtra(SecondRegisterActivity.PROVINCE, provinceAutoCompleteTextView.getText().toString());
        intent.putExtra(SecondRegisterActivity.CITY, cityAutoCompleteTextView.getText().toString());
        intent.putExtra(SecondRegisterActivity.GENDER, genderString);
        startActivity(intent);


    }


}