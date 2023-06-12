package com.example.pinqisandriodlabs;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.example.pinqisandriodlabs.databinding.ActivitySecondBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    protected ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ActivitySecondBinding.inflate(getLayoutInflater());

        // loads the XML files /res/layout/activity_second.xml
//        setContentView(R.layout.activity_second);

        super.onCreate(savedInstanceState);
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

        File file = new File( getFilesDir(), "Picture.png");
        if(file.exists()) {
            Bitmap theImage = BitmapFactory.decodeFile(file.getAbsolutePath());
            binding.profileImage.setImageBitmap(theImage);
        }

        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Bitmap thumbnail = data.getParcelableExtra("data");
                            binding.profileImage.setImageBitmap(thumbnail);
                            FileOutputStream fOut = null;
                            try {
                                fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);
                                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                                fOut.flush();
                                fOut.close();
                            }
                            catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            catch(IOException ioe) {
                                ioe.printStackTrace();
                            }
                        }
                    }
                }
        );

        binding.changePicButton.setOnClickListener( (v) -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                    cameraResult.launch(cameraIntent);
                else
                    requestPermissions(new String[] {Manifest.permission.CAMERA}, 20);
            }
        });

        binding.goBackButton.setOnClickListener((v)->{
            //opposite of startActivity
            finish(); //go back to previous

        });







    }
}