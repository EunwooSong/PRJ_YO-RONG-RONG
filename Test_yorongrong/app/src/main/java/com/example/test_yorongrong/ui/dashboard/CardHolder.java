package com.example.test_yorongrong.ui.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_yorongrong.R;

public class CardHolder extends RecyclerView.ViewHolder {
    ImageView mImageView;
    TextView  mTitle;

    public CardHolder(@NonNull View view) {
        super(view);
        mImageView = view.findViewById(R.id.imageview_main_image2);
        mTitle = view.findViewById(R.id.imageview_title);
    }
}
