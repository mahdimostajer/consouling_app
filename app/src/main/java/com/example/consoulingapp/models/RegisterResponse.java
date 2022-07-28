package com.example.consoulingapp.models;

public class RegisterResponse {
    public String phone_number;
    public String date_joined;
    public String province;
    public String type;
    public String gender;
    public String first_name;
    public String last_name;
    public String credit_card_no;
    public String birth_date;
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
