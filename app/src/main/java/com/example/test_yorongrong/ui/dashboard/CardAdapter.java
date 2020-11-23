package com.example.test_yorongrong.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_yorongrong.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardHolder> {
    Context c;
    ArrayList<Model> models;

    public CardAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup,false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder myHolder, int i) {
        myHolder.mTitle.setText(models.get(i).getTitle());
        myHolder.mImageView.setImageResource(models.get(i).getImg());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
