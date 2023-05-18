package com.example.pinqisandriodlabs.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.pinqisandriodlabs.R;
import com.example.pinqisandriodlabs.data.MainActivityViewModel;
import com.example.pinqisandriodlabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding variableBinding;
    MainActivityViewModel model;

    // equivalent to    static void main(String args[])
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainActivityViewModel.class);


        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        variableBinding.theText.setText(model.theText);
        variableBinding.theButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String words = variableBinding.theEditText.getText().toString();

                // change what's in the textView
                variableBinding.theText.setText(words);

            }
        });

        variableBinding.theButton.setOnClickListener((click) -> {
            model.theText = "you clicked me";
            variableBinding.theText.setText("model.theText");
        });





        // loads an XML file on the page
//        setContentView(R.layout.activity_main);  //not need anymore after viewBinding
    }
}