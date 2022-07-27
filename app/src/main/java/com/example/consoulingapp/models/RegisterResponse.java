package com.example.consoulingapp.models;

public class RegisterResponse {
    String phone_number;
    String date_joined;
    String province;
    String type;
    String gender;
    String first_name;
    String last_name;
    String credit_card_no;
    String birth_date;
    public RegisterResponse(
            String phone_number,
            String date_joined,
            String province,
            String type,
            String gender,
            String first_name,
            String last_name,
            String credit_card_no,
            String birth_date
    ){
        this.phone_number = phone_number;
        this.date_joined = date_joined;
        this.province = province;
        this.type = type;
        this.gender = gender;
        this.first_name = first_name;
        this.last_name = last_name;
        this.credit_card_no = credit_card_no;
        this.birth_date = birth_date;
    }

}
