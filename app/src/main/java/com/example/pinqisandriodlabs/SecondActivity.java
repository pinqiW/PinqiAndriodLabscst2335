package com.example.pinqisandriodlabs;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.pinqisandriodlabs.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());

        // loads the XML files /res/layout/activity_second.xml
//        setContentView(R.layout.activity_second);
       setContentView(binding.getRoot());

        Intent fromPrevious = getIntent(); // return the Intent what got us here

        // should have variable in fromPrevious
        String EMAIL = fromPrevious.getStringExtra("EMAIL"); // doesn't need a defualtValue cuz its null by default.
        int age = fromPrevious.getIntExtra("AGE",0);
        String day = fromPrevious.getStringExtra("DAY");
        int something = fromPrevious.getIntExtra("SOMETHING",0); // default is for when SOMETHING is not there

        binding.textView4.setText("Welcome Back: "  + EMAIL );


        binding.callButton.setOnClickListener((v)->{

            String phoneNumToCall = binding.phoneToCall.getText().toString();
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + phoneNumToCall));
            startActivity(call);
        });

        binding.changePicButton.setOnClickListener((v)->{
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult()
                    ,new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {

                                Intent data = result.getData();
                                Bitmap thumbnail = data.getParcelableExtra("data");
                                profileImage.setImageBitmap(thumbnail);
                            }
                        }
                    } );
            cameraResult.launch(cameraIntent);

        });

        binding.goBackButton.setOnClickListener((v)->{
            //opposite of startActivity
            finish(); //go back to previous

        });







    }
}