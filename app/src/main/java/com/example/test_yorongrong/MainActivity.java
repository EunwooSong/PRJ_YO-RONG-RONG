package com.example.test_yorongrong;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.test_yorongrong.ui.dashboard.DashboardFragment;
import com.example.test_yorongrong.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_FROM_ALBUM = 1;

    String[] permission_list = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE
    };

    FloatingActionButton capture_btn;
    private Animation animation_in;
    private Animation animation_out;

    HomeFragment home;
    DashboardFragment dash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navView = findViewById(R.id.bottomnav_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        animation_in = AnimationUtils.loadAnimation(this, R.anim.anim);
        animation_out = AnimationUtils.loadAnimation(this, R.anim.out_anim);

        home = new HomeFragment();
        dash =  new DashboardFragment();

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        capture_btn.show();
                        capture_btn.setVisibility(View.VISIBLE);
                        capture_btn.startAnimation(animation_in);
                        MainActivity.this.switchFragment(home);
                        break;

                    case R.id.navigation_dashboard:
                        capture_btn.hide();
                        capture_btn.startAnimation(animation_out);
                        capture_btn.setVisibility(View.GONE);
                        MainActivity.this.switchFragment(dash);
                        break;
                }
                return true;
            }
        });
        switchFragment(new HomeFragment());

        capture_btn = findViewById(R.id.btn_back);
        capture_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment home = (HomeFragment)getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                home.CaptureCamera();
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permission_list, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for(int a : grantResults) {
            if(a == PackageManager.PERMISSION_DENIED)
                return;
        }
    }
    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.commit();
    }

    public void goToAlbum() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FROM_ALBUM) {
            Uri photoUri = data.getData();
            Cursor cursor = null;

            try {
                String[] proj = {MediaStore.Images.Media.DATA};

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                File tempFile = new File(cursor.getString(column_index));
                SendCapture(tempFile.getPath());
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }

    public void SendCapture(String imgPath) {
        Intent intent = new Intent();
        ComponentName name = new ComponentName("com.example.test_yorongrong", "com.example.test_yorongrong.ui.result.ResultActivity");
        intent.setComponent(name);
        intent.putExtra("cameraImg", imgPath);

        startActivityForResult(intent, 100);
    }

}

// Passing each menu ID as a set of Ids because each
// menu should be considered as top level destinations.
//주석좀
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        //이거 눅가 한거야 lrmj
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
//그거 안드로이드 기본 에셋 중ㅇ ㅔ 네비게이션 바 있는걸 골라서 넣은거입니다 우리는 어떤 내비게이션 뷰를 ㅆ섯지? 바롤 머테리얼

//        setContentView(R.layout.activity_main);
