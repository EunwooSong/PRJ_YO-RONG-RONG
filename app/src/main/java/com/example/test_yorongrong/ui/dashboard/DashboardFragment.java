package com.example.test_yorongrong.ui.dashboard;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_yorongrong.Data.AllData;
import com.example.test_yorongrong.Data.model;
import com.example.test_yorongrong.R;
import com.example.test_yorongrong.api.NetworkHelper;
import com.example.test_yorongrong.ui.result.ServiceResult.Card.ResultModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private static final String BASE_URL = "https://scon.postect.tech/api";
    private DashboardViewModel dashboardViewModel;

    ArrayList<Model> models = new ArrayList<>();

    RecyclerView mRecycleView;
    CardAdapter mAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mRecycleView = root.findViewById(R.id.recyclerview);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getMyList();
        mAdapter = new CardAdapter(getActivity(), models);
        mRecycleView.setAdapter(mAdapter);
        return root;
    }

    public model[] APICommunication(String uuid) {
        final model[][] result = {new model[1]};
        NetworkHelper.getInstance(BASE_URL).GetAll(uuid).enqueue(new Callback<AllData>() {
            @Override
            public void onResponse(Call<AllData> call, Response<AllData> response) {
                AllData data = response.body();
                result[0] = data.getData();
            }

            @Override
            public void onFailure(Call<AllData> call, Throwable t) {

            }
        });

        return result[0];
    }
    private ArrayList<Model> getMyList() {

//        Model m = new Model();
//        m.setTitle_back("챈스챈스\n뒤집으면 카피논란이 있는 브랜드가 나옵니다");
//        m.setImg_back(R.drawable.chance_chance);
//        m.setTitle("체크메이트");
//        m.setImg(R.drawable.checkmate);
//        models.add(m);


        TelephonyManager tm = (TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String uuid = tm.getDeviceId();

        model[] data = APICommunication(uuid);

        for(model i: data) {
            Model m = new Model();
            m.setTitle(i.name);
            m.setTitle_back(i.name2);
            m.setImg_back(i.img);
            m.setImg(i.img2);
            models.add(m);
        }
        return models;
    }
}