package com.example.consoulingapp;

import android.graphics.Color;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.consoulingapp.databinding.ActivityMainBinding;
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

        fragmentManager = getSupportFragmentManager();

        // new instance is created and data is took from an
        // array list known as getDataonborading
        final PaperOnboardingFragment paperOnboardingFragment = onBoardFragment.newInstance(getDataforOnboarding());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // fragmentTransaction method is used
        // do all the transactions or changes
        // between different fragments
        fragmentTransaction.add(R.id.frame_layout, paperOnboardingFragment);

        // all the changes are committed
        fragmentTransaction.commit();
    }

    private ArrayList<PaperOnboardingPage> getDataforOnboarding() {

        // the first string is to show the main title ,
        // second is to show the message below the
        // title, then color of background is passed ,
        // then the image to show on the screen is passed
        // and at last icon to navigate from one screen to other
        PaperOnboardingPage source = new PaperOnboardingPage("Gfg", "Welcome to GeeksForGeeks", Color.parseColor("#ffffff"),R.drawable.ic_test, R.drawable.ic_test);
        PaperOnboardingPage source1 = new PaperOnboardingPage("Practice", "Practice questions from all topics", Color.parseColor("#ffffff"),R.drawable.ic_test, R.drawable.ic_test);
        PaperOnboardingPage source2 = new PaperOnboardingPage("hmmm", " jkhgjkl", Color.parseColor("#ffffff"),R.drawable.ic_test, R.drawable.ic_test);

        // array list is used to store
        // data of onbaording screen
        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();

        // all the sources(data to show on screens)
        // are added to array list
        elements.add(source);
        elements.add(source1);
        elements.add(source2);
        return elements;
    }
}