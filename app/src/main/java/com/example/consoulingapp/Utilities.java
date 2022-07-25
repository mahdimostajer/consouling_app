package com.example.consoulingapp;

import com.google.android.material.textfield.TextInputLayout;

public class Utilities {
    static public String getTextInputLayoutValue(TextInputLayout textInputLayout){
        return textInputLayout.getEditText().getText().toString();

    }
}
