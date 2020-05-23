package com.example.cardie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<SpinnerItem> {


    public SpinnerAdapter(Context context, ArrayList<SpinnerItem> itemList){
        super(context,0, itemList);

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }



    public View initView(int position,View convertView,ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_item, parent, false
            );
        }
        TextView itemName= convertView.findViewById(R.id.week_counter);
        SpinnerItem currentItem = getItem(position);
        if (currentItem != null){
            //System.out.println(currentItem.getItemName());
            itemName.setText(currentItem.getItemName());
        }
        return convertView;
    }
}
