package com.example.test_yorongrong.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_yorongrong.R;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {
    Context c;
    ArrayList<Model> models;
    ItemClickListener mClickListener;

    public CardAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dash_card, viewGroup,false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardHolder myHolder, int i) {
        myHolder.mTitle.setText(models.get(i).getTitle());
        myHolder.mImageView.setImageResource(models.get(i).getImg());
        myHolder.mTitle_back.setText(models.get(i).getTitle_back());
        myHolder.mImageView_back.setImageResource(models.get(i).getImg_back());

        myHolder.flipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHolder.flipView.flipTheView();
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    //CardHolder
    public class CardHolder extends RecyclerView.ViewHolder {
        EasyFlipView flipView;
        ImageView mImageView;
        ImageView mImageView_back;
        TextView mTitle;
        TextView mTitle_back;

        public CardHolder(@NonNull View view) {
            super(view);
            flipView = view.findViewById(R.id.dash_flip);
            mImageView = view.findViewById(R.id.imageview_main_image2);
            mImageView_back = view.findViewById(R.id.imageview_main_image2_back);
            mTitle = view.findViewById(R.id.imageview_title);
            mTitle_back = view.findViewById(R.id.imageview_title_back);
        }
    }

    Model getItem(int id) {
        return models.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public class ItemClickListener {
        void onItemClick(View view, int position) {

        }
    }
}
