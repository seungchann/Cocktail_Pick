package com.example.cocktail_pick.MyPageTab;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.Data.Recipe;
import com.example.cocktail_pick.HomeTab.SummaryAdapter;
import com.example.cocktail_pick.R;

import java.util.ArrayList;

public class MyRecipeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Recipe> my_recipes;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage_cart, container, false);

        init_my_recipes();
        recyclerView = rootView.findViewById(R.id.mypage_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new SummaryAdapter(getActivity(), my_recipes));

        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void init_my_recipes() {
        my_recipes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            my_recipes.add(new Recipe("피치 크러쉬"));
        }
    }
}
