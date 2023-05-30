package com.example.pinqisandriodlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pinqisandriodlabs.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());

        // loads the XML files /res/layout/activity_second.xml
        setContentView(R.layout.activity_second);

        binding.goBackButton.setOnClickListener((v)->{

            //opposite of startActivity
            finish(); //go back to previous

        });










    }
}