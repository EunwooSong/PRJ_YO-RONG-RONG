package com.example.test_yorongrong.ui.result.ServiceResult.Card;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_yorongrong.R;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultHolder> {
    Context c;
    ArrayList<ResultModel> models;

    public ResultAdapter(Context c, ArrayList<ResultModel> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_card, parent,false);
        return new ResultHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
        Log.i("model", "test : " + models.get(position).getTitle());

        holder.mTitle.setText(models.get(position).getTitle());
        holder.mImageView.setImageResource(models.get(position).getImg());
        holder.mShopInfo.setText(models.get(position).getShop_info());
        holder.mPresent.setText(models.get(position).getPresent());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
