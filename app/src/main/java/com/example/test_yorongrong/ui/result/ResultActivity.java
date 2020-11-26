package com.example.test_yorongrong.ui.result;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test_yorongrong.R;
import com.example.test_yorongrong.ui.home.HomeFragment;
import com.example.test_yorongrong.ui.result.ServiceResult.ResultFragment;
import com.example.test_yorongrong.ui.result.loading.LoadingFragment;

public class ResultActivity extends AppCompatActivity {

    LoadingFragment loading;
    ResultFragment result;

    public Bitmap img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Intent intent = getIntent();
        byte[] data = getIntent().getByteArrayExtra("cameraImg");
        img = BitmapFactory.decodeByteArray(data, 0, data.length);
        img = rotateBmp(img);

        if(img == null)
            Log.e("error", "img null");

        loading = new LoadingFragment();
        result = new ResultFragment();

        //First Fragment
        FragmentTransaction trans =  getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.result_fragment, loading);
        trans.commit();
        loading.setImageView(data);

        ((Button)findViewById(R.id.btn_test)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction trans = manager.beginTransaction();

                trans.replace(R.id.result_fragment, result);
                trans.commit();
            }
        });
    }

    public Bitmap rotateBmp(Bitmap bmp){
        Matrix matrix = new Matrix();

        matrix.postRotate(90);

        Bitmap result = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),bmp.getHeight(), matrix, true);

        return result;
    }
}