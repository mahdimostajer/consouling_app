package com.example.consoulingapp.models;

public class ActiveCourse {
    public int final_price;
    public boolean is_confirmed;
    public boolean is_active;
    public String start_date;
    public String end_date;
    public Course course;
    public ProfileResponse student;
    public ProfileResponseConsultant consultant;
    public int total_days;
    public int days_left;
    public boolean is_paid;
    public boolean is_paid_to_consultant;
}
