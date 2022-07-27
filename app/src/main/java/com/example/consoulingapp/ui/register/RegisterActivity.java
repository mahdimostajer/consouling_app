package com.example.consoulingapp.ui.register;

import static com.example.consoulingapp.Utilities.getTextInputLayoutValue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.consoulingapp.databinding.ActivityRegisterBinding;
import com.example.consoulingapp.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;


public class RegisterActivity extends AppCompatActivity {


    TextInputLayout usernameInput,passwordInput,confirmPasswordInput;
    RadioGroup typeInput;
    static public String USERNAME = "RegisterActivityUsername";
    static public String PASSWORD = "RegisterActivityPassword";
    static public String TYPE = "RegisterActivityType";

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        usernameInput = binding.registerUsername;
        passwordInput = binding.registerPassword;
        confirmPasswordInput = binding.registerConfirmPassword;
        typeInput = binding.type;




    }


    public void callNextRegisterScreen(View view) {
        int type = typeInput.getCheckedRadioButtonId();
        String typeString;
        if(type == 0){
            typeString = "student";
        }
        else{
            typeString = "consultant";
        }
        if (!validatePassword()) return;//todo: validate other fields
        Intent intent = new Intent(getApplicationContext(), SecondRegisterActivity.class);
        intent.putExtra(RegisterActivity.USERNAME, getTextInputLayoutValue(usernameInput));
        intent.putExtra(RegisterActivity.PASSWORD, getTextInputLayoutValue(passwordInput));
        intent.putExtra(RegisterActivity.TYPE, typeString);
        startActivity(intent);

    }



    public void callLoginFromRegister(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }




    private boolean validatePassword() {
        String passwordVal = getTextInputLayoutValue(passwordInput);
        String confirmPasswordVal = getTextInputLayoutValue(confirmPasswordInput);
        if (confirmPasswordVal.isEmpty()) {
            confirmPasswordInput.setError("تکرار رمز عبور نمیتواند خالی باشد");
            return false;
        }
        if (passwordVal.isEmpty()) {
            confirmPasswordInput.setError("رمز عبور نمیتواند خالی باشد");
            return false;
        } else if (!passwordVal.equals(confirmPasswordVal)) {
            confirmPasswordInput.setError("عدم تطابق با رمز عبور");
            return false;
        } else {
            confirmPasswordInput.setError(null);
            confirmPasswordInput.setErrorEnabled(false);
            passwordInput.setError(null);
            passwordInput.setErrorEnabled(false);
            return true;
        }

    }

//    private boolean validateFullName() {
//        String val = fullName.getEditText().getText().toString().trim();
//        if (val.isEmpty()) {
//            fullName.setError("Field can not be empty");
//            return false;
//        } else {
//            fullName.setError(null);
//            fullName.setErrorEnabled(false);
//            return true;
//        }
//    }


}