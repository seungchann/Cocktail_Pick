package com.example.cocktail_pick.RecommendTab;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cocktail_pick.Data.Cocktail;
import com.example.cocktail_pick.HomeTab.SummaryAdapter;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.Recipe;

import java.util.ArrayList;

public class RecommendTabFragment extends Fragment {
    ViewPager2 viewPager;
    ArrayList<Cocktail> cocktails;
    RecommendViewPagerAdapter buying_adapter;
    RecyclerView recommend_recycler_view;
    SummaryAdapter summary_adapter;
    Button addBtn, removeBtn;

    ArrayList<Recipe> recommendRecipes;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recommend, container, false);

        initCocktails();
        initRecommendRecipes();
        viewPager = rootView.findViewById(R.id.buy_view_pager);
        buying_adapter = new RecommendViewPagerAdapter(getActivity(), cocktails);
        viewPager.setAdapter(buying_adapter);

        recommend_recycler_view = rootView.findViewById(R.id.recommend_recycler_view);
        recommend_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
//        summary_adapter = new SummaryAdapter(getActivity(), recommendRecipes);
        recommend_recycler_view.setAdapter(summary_adapter);

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    void initRecommendRecipes() {
        recommendRecipes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            recommendRecipes.add(new Recipe());
        }
    }
}
