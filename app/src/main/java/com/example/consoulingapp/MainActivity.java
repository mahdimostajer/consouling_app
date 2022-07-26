package com.example.consoulingapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.consoulingapp.databinding.ActivityMainBinding;
import com.example.consoulingapp.ui.login.LoginActivity;
import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        fragmentManager = getSupportFragmentManager();

        final PaperOnboardingFragment paperOnboardingFragment = PaperOnboardingFragment.newInstance(getDataforOnboarding());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.frame_layout, paperOnboardingFragment);

        fragmentTransaction.commit();
    }

    private ArrayList<PaperOnboardingPage> getDataforOnboarding() {

        PaperOnboardingPage source = new PaperOnboardingPage("ارتباط با مشاور تحصیلی", "در اپلیکیشن ما می\u200Cتوانید در طول روز بدون محدودیت با مشاور خودتون حرف بزنید و سوال\u200Cهاتون رو بپرسید.", Color.parseColor("#FFD95D"),R.drawable.android, R.drawable.ic_onboarding );
        PaperOnboardingPage source1 = new PaperOnboardingPage("برنامه\u200Cریزی و مدیریت زمان", "ثبت ساعات مطالعه، خواب و تفریح در یک اپلیکیشن و دسترسی همیشگی به آنها به بازدهی شما کمک می\u200Cکند.", Color.parseColor("#0011A8"), R.drawable.anroid2,R.drawable.ic_onboarding);
        PaperOnboardingPage source2 = new PaperOnboardingPage("تست\u200Cزنی و دریافت پاسخ", "ارزیابی مهم\u200Cترین مرحله در آموزش است، به کمک تست\u200Cهای آنلاین و تصحیح آنلاین ما از دیگران پیش بیافتید.", Color.parseColor("#A80000"), R.drawable.android3,R.drawable.ic_onboarding);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();

        elements.add(source);
        elements.add(source1);
        elements.add(source2);
        return elements;
    }
}