package com.example.pinqisandriodlabs.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pinqisandriodlabs.R;
import com.example.pinqisandriodlabs.data.MainActivityViewModel;
import com.example.pinqisandriodlabs.databinding.ActivityMainBinding;
//The Model - View - ViewModel pattern
//View - This represents the page on screen.
// In Android, this is the AppCompatActivity class. This class uses ViewBinding to hold all of the Widgets on the page.

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;
    private MainActivityViewModel model;

    // equivalent to    static void main(String args[])
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        connect the AppCompatActivity(ui) to viewModel(data)
        model = new ViewModelProvider(this).get(MainActivityViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(variableBinding.getRoot());

        variableBinding.theText.setText(model.theText);
//        variableBinding.theButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // get what's in the EditText widget
//                String words = variableBinding.theEditText.getText().toString();
//
//                // change what's in the TextView widget
//                variableBinding.theText.setText("Your edit text has: " + words);
//
//            }
//        });

        variableBinding.theButton.setOnClickListener((click) -> {
//            model.theText = "you clicked me";
//            variableBinding.theText.setText(model.theText);

            model.editString.postValue(variableBinding.theEditText.getText().toString());
        });


        model.editString.observe(this, s -> {
            variableBinding.theText.setText("Your edit text has: " + s);
        });


        // observe changes in coffee selection
        model.coffeeOrNot.observe(this, selected -> {
            variableBinding.checkBox.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.switch1.setChecked(selected);

            CharSequence text = "The value is now: " + model.coffeeOrNot.getValue();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
            toast.show();

        });

        //checkBox
        variableBinding.checkBox.setOnCheckedChangeListener((btn, isChecked) -> {
            model.coffeeOrNot.postValue(isChecked);
        });

        //radioButton
        variableBinding.radioButton.setOnCheckedChangeListener((btn, isChecked) -> {
            model.coffeeOrNot.postValue(isChecked);
        });

        //switch
        variableBinding.switch1.setOnCheckedChangeListener((btn, isChecked) -> {
            model.coffeeOrNot.postValue(isChecked);
        });

        // loads an XML file on the page
//        setContentView(R.layout.activity_main);  //not need anymore after viewBinding
    }
}