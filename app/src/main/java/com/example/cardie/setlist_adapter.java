package com.example.cardie;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardie.Models.CardieSet;

import java.util.List;

public class setlist_adapter extends RecyclerView.Adapter<setlist_adapter.MyViewHolder> {
    private Context mContext;
    private List<CardieSet> mData;
    static SetListener listener;

    public setlist_adapter(Context mContext, List<CardieSet> mData, SetListener listener) {
        this.mContext = mContext;
        this.mData = mData;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.activity_card_single,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final CardieSet mDrawable = mData.get(position);
        //SetImg turned off for testing API
//        int mDrawableName = mData.get(position).getSetImgUrl(); //R.drawable.bunny_Sweden
//        Drawable d = ResourcesCompat.getDrawable(mContext.getResources(),mDrawableName,null);
//        holder.set_card.setBackground(d);
        holder.set_title.setText(mData.get(position).getSetName());
        //holder.imageView1.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.backgroundbluelight,null));
        holder.set_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSetClick(mDrawable);
            }
        });
        holder.practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPracticeButtonClick(mDrawable.getSetName());
            }
        });
        switch (mDrawable.getDifficulty()) {
            case 1: {
                //holder.imageView1.setBackgroundResource(R.drawable.circlefull);
                //holder.imageView1.setColorFilter(Color.RED);
                holder.imageView1.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.greendiff,null));
                break;
            }
            case 2: {
                holder.imageView1.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.greendiff,null));
                holder.imageView2.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.backgroundOrange,null));
                break;
            }
            case 3: {
                holder.imageView1.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.greendiff,null));
                holder.imageView2.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.backgroundOrange,null));
                holder.imageView3.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.backgroundPurple,null));
                break;
            }
            default: break;
        }
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView set_title;
        TextView set_intro;
        CardView set_card;
        String set_img_url;
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
        Button practice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            set_title =  itemView.findViewById(R.id.myset_title);
            set_card =  itemView.findViewById(R.id.cardview);
            imageView1 = itemView.findViewById(R.id.diff1);
            imageView2 = itemView.findViewById(R.id.diff2);
            imageView3 = itemView.findViewById(R.id.diff3);
            practice = itemView.findViewById(R.id.let_practice);
        }

    }
}

