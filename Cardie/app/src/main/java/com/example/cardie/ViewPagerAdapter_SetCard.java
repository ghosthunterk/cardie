package com.example.cardie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.cardie.Models.Card;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPagerAdapter_SetCard extends PagerAdapter {
    private List<Card> mData;
    private LayoutInflater layoutInflater;
    private Context mContext;
    ViewGroup Container;
    View view;
    String[] imageurl;
    int c=0;

    static CardView cardView;

    public ViewPagerAdapter_SetCard(List<Card> models,Context context)
    {
        this.mData = models;
        this.mContext = context;
   /*     layoutInflater = LayoutInflater.from(context);*/
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    private View initObject(ViewGroup container, int position)
    {
        layoutInflater = LayoutInflater.from(mContext);
        final View view1 = layoutInflater.inflate(R.layout.card_in_set_template,container,false);
        cardView = view1.findViewById(R.id.CardTemplate);
/*        String mDrawableName = mData.get(position).getCardImageUrl(); //R.drawable.bunny_Sweden*/
        ImageView imgView = view1.findViewById(R.id.CardBackground);
/*        imgView.setImageResource(mDrawableName);*/
        System.out.println(mData.get(position).getCardImageUrl());
        Picasso.get()
                .load(mData.get(position).getCardImageUrl())
                .fit()
                .centerCrop()
                .into(imgView);
        TextView cardName = view1.findViewById(R.id.CardName);
        TextView cardType = view1.findViewById(R.id.CardNameType);
        final TextView cardDef = view1.findViewById(R.id.card_in_set_definition);
        cardName.setText(mData.get(position).getCardWord());
        cardType.setText(mData.get(position).getCardType());
        cardDef.setText(mData.get(position).getCardDefinition());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout infoLayout = view1.findViewById(R.id.basicInfo);
                infoLayout.setVisibility(View.INVISIBLE);
                cardDef.setVisibility(View.VISIBLE);
            }

        });

//        LinearLayout cardNameLayout = view1.findViewById()
        return view1;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        view = initObject(container,position);
        view.setTag("currentCardView"+ position);
        container.addView(view);
        Container = container;
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

//    public void getCurrentView(int position)
//    {
//
//    }
}
