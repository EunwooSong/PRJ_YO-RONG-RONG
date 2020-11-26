package com.example.test_yorongrong.ui.dashboard;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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

import com.example.test_yorongrong.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DashboardFragment extends Fragment {

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

    private ArrayList<Model> getMyList() {

        Model m = new Model();

        m.setTitle_back("챈스챈스");
        m.setImg_back(R.drawable.chance_chance);
        m.setTitle("체크메이트");
        m.setImg(R.drawable.checkmate);
        models.add(m);

        m = new Model();
        m.setTitle_back("트레셔");
        m.setImg_back(R.drawable.thrasher);
        m.setTitle("어커버");
        m.setImg(R.drawable.acover);
        models.add(m);

        m = new Model();
        m.setTitle_back("디스이즈네버뎃");
        m.setImg_back(R.drawable.chance_chance);
        m.setTitle("플루크");
        m.setImg(R.drawable.checkmate);
        models.add(m);

        m = new Model();
        m.setTitle_back("오아이오아이");
        m.setImg_back(R.drawable.oioi);
        m.setTitle("척");
        m.setImg(R.drawable.chuck);
        models.add(m);

        m = new Model();
        m.setTitle_back("유엔지레이어");
        m.setImg_back(R.drawable.unglayer);
        m.setTitle("야키요루");
        m.setImg(R.drawable.yakiyo);
        models.add(m);

        m = new Model();
        m.setTitle_back("메종 마르지엘라");
        m.setImg_back(R.drawable.mejong);
        m.setTitle("피스메이커");
        m.setImg(R.drawable.peace_maker);
        models.add(m);

        return models;
    }
}