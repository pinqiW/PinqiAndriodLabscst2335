package com.example.pinqisandriodlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    // equivalent to    static void main(String args[])
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // loads an XML file on the page
        setContentView(R.layout.activity_main);

        // look for something with the id "theText", return the object
        TextView theText = findViewById(R.id.theText); // same as findElementById
        Button b = findViewById(R.id.theButton);
        EditText theEdit = findViewById( R.id.theEditText);


        // anonymous class
        b.setOnClickListener(new View.OnClickListener() {
            // provide the missing functions:
            @Override
            public void onClick(View v) {

                // return what's in the EditText
                String words = theEdit.getText().toString();

                // change what's in the textView
                theText.setText(words);

            }
        });


    }

}