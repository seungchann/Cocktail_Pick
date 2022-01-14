package com.example.cocktail_pick.RecommendTab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cocktail_pick.R;

import java.util.ArrayList;

public class RecommendTabFragment extends Fragment {
    ViewPager2 viewPager;
    ArrayList<Cocktail> cocktails;
    RecommendViewPagerAdapter buying_adapter;
    Button addBtn, removeBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recommend, container, false);

        initCocktails();
        viewPager = rootView.findViewById(R.id.buy_view_pager);
        buying_adapter = new RecommendViewPagerAdapter(getActivity(), cocktails);
        viewPager.setAdapter(buying_adapter);

        addBtn = rootView.findViewById(R.id.add_buying_btn);
        removeBtn = rootView.findViewById(R.id.remove_buying_btn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buying_adapter.cancel_flag = !buying_adapter.cancel_flag;
                buying_adapter.notifyDataSetChanged();
            }
        });


        return rootView;
    }

    void initCocktails() {
        cocktails = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            cocktails.add(new Cocktail("잭 다니엘", ""));
        }
    }
}
