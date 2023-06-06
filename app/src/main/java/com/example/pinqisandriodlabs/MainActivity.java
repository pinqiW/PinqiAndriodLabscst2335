package com.example.pinqisandriodlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.pinqisandriodlabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";

    protected ActivityMainBinding binding;
    // equivalent to    static void main(String args[])
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "In OnCreate()");

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // loads an XML file on the page
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main);

        Log.w(TAG, "In onCreate()-Loading Widgets");

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "You clicked the login Button");
                //            //where to go :                   leaving here          going here
                Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);

                String whatIsTyped = binding.emailText.getText().toString();
                nextPage.putExtra("EMAIL",whatIsTyped);
                nextPage.putExtra("AGE",25);
                nextPage.putExtra("DAY", "Tuesday");

                startActivity(nextPage); //carries all the data to the next page;



//
//                Intent intent2 = new Intent(Intent.ACTION_VIEW);
//                intent2.setData(Uri.parse("https://www.algonquincollege.com"));

//
//                startActivity(call);
            }
        });


    }




    @Override //garbage collected, app is gone
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"In Destroy()");
        Log.w(TAG,"In Destroy()- Any memory used by the application is freed");

    }

    @Override // now visible, not listening for clicks
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"In OnStart()");
        Log.w(TAG,"In onStart()-the application is now visible on screen");

    }

    @Override // no longer visible on screen
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"In OnResume()");
        Log.w(TAG,"In onResume()-The application is now responding to user input");


    }

    @Override // leaving the screen , on longer listen for input
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"In OnPause()");
        Log.w(TAG,"In onPause()-The application no longer responds to user input");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG,"In Stop()-The application is no longer visible");
    }
}