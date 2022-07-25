package com.example.consoulingapp.models;

public class RegisterResponse {
    String username;
    String password;
    String type;
    String gender;
    String firstName;
    String lastName;
    String creditCard;
    String date;
    public RegisterResponse(
            String username,
            String password,
            String type,
            String gender,
            String firstName,
            String lastName,
            String creditCard,
            String date
    ){
        this.username = username;
        this.password = password;
        this.type = type;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creditCard = creditCard;
        this.date = date;
    }

}
