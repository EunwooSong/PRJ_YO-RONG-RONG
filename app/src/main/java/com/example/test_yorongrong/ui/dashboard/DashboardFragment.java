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

        m.setTitle("Test Model");
        m.setImg(R.drawable.images);
        models.add(m);

        m = new Model();
        m.setTitle("Test Model");
        m.setImg(R.drawable.images);
        models.add(m);

        m = new Model();
        m.setTitle("Test Model");
        m.setImg(R.drawable.images);
        models.add(m);

        m = new Model();
        m.setTitle("Test Model");
        m.setImg(R.drawable.images);
        models.add(m);

        m = new Model();
        m.setTitle("Test Model");
        m.setImg(R.drawable.images);
        models.add(m);

        m = new Model();
        m.setTitle("Test Model");
        m.setImg(R.drawable.images);
        models.add(m);

        return models;
    }
}