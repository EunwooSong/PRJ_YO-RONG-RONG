package com.example.test_yorongrong;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.test_yorongrong.ui.dashboard.DashboardFragment;
import com.example.test_yorongrong.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


    String[] permission_list = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
    };

    FloatingActionButton capture_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navView = findViewById(R.id.bottomnav_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //java8.....

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        MainActivity.this.switchFragment(new HomeFragment());
                        break;
                    case R.id.navigation_dashboard:
                        MainActivity.this.switchFragment(new DashboardFragment());
                        break;
                }
                return true;
            }
        });
        switchFragment(new HomeFragment());

        capture_btn = findViewById(R.id.fab_main);
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
