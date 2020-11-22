package com.example.test_yorongrong.ui.result;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test_yorongrong.R;

public class CameraActivity extends AppCompatActivity {

    Bitmap img = null;
    ImageView imageView;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK) {
            img = (Bitmap) data.getParcelableExtra("cameraImg");
            imageView.setImageBitmap(img);
            Toast.makeText(getApplicationContext(), "Create Image", (int) 3).show();
        }

        Toast.makeText(getApplicationContext(), "Failed to Create Image", (int) 3).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Intent intent = getIntent();
        img = (Bitmap) intent.getParcelableExtra("cameraImg");

        imageView = (ImageView) findViewById(R.id.cameraImg);
        imageView.setImageBitmap(img);

        Toast.makeText(getApplicationContext(), "Create Image", (int) 3).show();
    }
}