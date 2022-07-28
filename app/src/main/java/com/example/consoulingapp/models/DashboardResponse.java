package com.example.consoulingapp.models;

public class DashboardResponse {
    int final_price;
    boolean is_confirmed;
    boolean is_active;
    String start_date;
    String end_date;
    Course course;
    ProfileResponse student;
    ProfileResponseConsultant consultant;
    int total_days;
    int days_spent;
    boolean is_paid;
    boolean is_paid_to_consultant;

}
