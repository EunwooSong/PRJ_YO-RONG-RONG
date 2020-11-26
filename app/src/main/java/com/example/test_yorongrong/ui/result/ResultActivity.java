package com.example.test_yorongrong.ui.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.test_yorongrong.Data.Data;
import com.example.test_yorongrong.R;
import com.example.test_yorongrong.api.NetworkHelper;
import com.example.test_yorongrong.ui.result.ServiceResult.ResultFragment;
import com.example.test_yorongrong.ui.result.loading.LoadingFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {
    LoadingFragment loading;
    ResultFragment result;

    private final TelephonyManager tm = (TelephonyManager)getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

    private static final String BASE_URL = "https://scon.postect.tech/api/";
    private final String tmDevice = "" + this.tm.getDeviceId();
    private final String tmSerial = "" + this.tm.getSimSerialNumber();
    private final String androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

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

        // api communication
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String phone_id = deviceUuid.toString();

        NetworkHelper.getInstance(BASE_URL).Compare(phone_id, "").enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data data = response.body();
                data.getImg();

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });

//        ((Button)findViewById(R.id.btn_test)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switchFragment(result);
//            }
//        });

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




    //ApiService에 적어놓았던 꼴리는 함수이) 하면 뭐 뜰꺼임 그거 하면 됌 그러면 Success메소드 faiulre메소드 둘다 오버라이딩 됌 이거 인터페이스 ㅂ라서 임플레ㅣ먼ㅊ츠됌
    //데이터 클래스 변수  = response.body(); 하면 데이터클래스에 값이 이쁘게 들어갈거야
    //데이터 클래스에 그리고 겟터 셋터 해줘서 단축키 -> 컨트롤 n 데이터클래스 변수.get너가 원하는거()하면 이제 그 값만 가져올수 있다 이말이야


    public Bitmap rotateBmp(Bitmap bmp){
        Matrix matrix = new Matrix();

        matrix.postRotate(90);

        Bitmap result = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),bmp.getHeight(), matrix, true);

        return result;
    }
}