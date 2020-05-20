package com.example.cardie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ViewPagerAdapter_SetResult  extends PagerAdapter {
    private LayoutInflater layoutInflater;
    Context mContext;
    List<Card> mData;
    static TextView textView;
    View view;
    ViewGroup Container;

    public ViewPagerAdapter_SetResult(Context mContext, List<Card> mData) {
        this.mContext = mContext;
        this.mData = mData;
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
        View view1 = layoutInflater.inflate(R.layout.test_template,container,false);
        textView=view1.findViewById(R.id.test_definition);
        String definition = mData.get(position).getCardDefinition();
        textView.setText(definition);
        return view1;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        view = initObject(container,position);
        container.addView(view);
        Container = container;
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
