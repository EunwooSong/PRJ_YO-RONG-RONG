package com.example.test_yorongrong.ui.result.ServiceResult.Card;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResultHolder extends RecyclerView.ViewHolder {
    ImageView mImageView;
    TextView mTitle;
    TextView mShopInfo;
    TextView mPresent;

    public ResultHolder(@NonNull View itemView) {
        super(itemView);
    }
}
