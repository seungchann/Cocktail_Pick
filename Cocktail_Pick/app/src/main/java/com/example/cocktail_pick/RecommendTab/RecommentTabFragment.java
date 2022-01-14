package com.example.cocktail_pick.RecommendTab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cocktail_pick.R;

import java.util.ArrayList;

public class RecommentTabFragment extends Fragment {
    ViewPager2 viewPager;
    ArrayList<Cocktail> cocktails;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);

        initCocktails();
        viewPager = rootView.findViewById(R.id.buy_view_pager);
        viewPager.setAdapter(new RecommendViewPagerAdapter(getActivity(), cocktails));


        return rootView;
    }

    void initCocktails() {
        cocktails = new ArrayList<>();
        cocktails.add(new Cocktail("잭 다니엘", ""));
    }
}
