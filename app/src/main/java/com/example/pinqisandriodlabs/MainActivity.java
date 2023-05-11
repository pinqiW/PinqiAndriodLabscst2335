package com.example.pinqisandriodlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



    // equivalent to    static void main(String args[])
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // loads an XML file on the page
        setContentView(R.layout.activity_main);
    }
}