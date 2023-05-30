package com.example.pinqisandriodlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.example.pinqisandriodlabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";

    protected ActivityMainBinding binding;
    // equivalent to    static void main(String args[])
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG,"In OnCreate()");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // loads an XML file on the page
        setContentView(R.layout.activity_main);

        binding.loginButton.setOnClickListener((v) -> {
            Log.e(TAG,"You clicked the login Button");

            //where to go :                   leaving here          going here
            Intent nextPage = new Intent(this, SecondActivity.class);

            // go to another page
            startActivity(nextPage);
        });
    }

    @Override //garbage collected, app is gone
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"In Destroy()");
    }

    @Override // now visible, not listening for clicks
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"In OnStart()");
    }

    @Override // no longer visible on screen
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"In OnResume()");

    }

    @Override // leaving the screen , on longer listen for input
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"In OnPause()");
    }
}