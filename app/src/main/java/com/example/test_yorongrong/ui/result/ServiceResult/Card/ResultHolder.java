package com.example.test_yorongrong.ui.result.ServiceResult.Card;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_yorongrong.R;

public class ResultHolder extends RecyclerView.ViewHolder {
    ImageView mImageView;
    TextView mTitle;
    TextView mShopInfo;
    TextView mPresent;

    public ResultHolder(@NonNull View view) {
        super(view);
        mImageView = view.findViewById(R.id.image_shop);
        mTitle = view.findViewById(R.id.imageview_title);
        mShopInfo = view.findViewById(R.id.result_info);
        mPresent = view.findViewById(R.id.result_present);
    }
}
