package com.example.cocktail_pick.HomeTab.DetailRecipe;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktail_pick.Data.Base;
import com.example.cocktail_pick.R;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {

    Base[] bases;
    LayoutInflater inflater;
    public SpinnerAdapter(Context context) {
        bases = Base.values();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bases.length;
    }

    @Override
    public Object getItem(int i) {
        return bases[i].toString();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_spinner, viewGroup, false);
        }

        if (bases != null) {
            String text = bases[i].toString();
            ((TextView) view.findViewById(R.id.spinner_item_tv)).setText(text);
        }
        return view;
    }
}
