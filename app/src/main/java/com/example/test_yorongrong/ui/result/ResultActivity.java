package com.example.test_yorongrong.ui.result;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ResultActivity extends AppCompatActivity {

    LoadingFragment loading;
    ResultFragment result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Intent intent = getIntent();
        String path = intent.getStringExtra("cameraImg");
        Log.i("test", "path : " + path);

        loading = new LoadingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("path", path);
        loading.setArguments(bundle);

        result = new ResultFragment();
        result.setArguments(bundle);

        //First Fragment
        switchFragment(loading);


        ((Button)findViewById(R.id.btn_test)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(result);
            }
        });

        ((FloatingActionButton)findViewById(R.id.btn_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.replace(R.id.result_fragment, fragment);
        transaction.commit();
    }


    public Bitmap rotateBmp(Bitmap bmp){
        Matrix matrix = new Matrix();

        matrix.postRotate(90);

        Bitmap result = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),bmp.getHeight(), matrix, true);

        return result;
    }
}