package com.example.cocktail_pick;

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

import java.util.ArrayList;

public class TestFragment extends Fragment {
    ArrayList<Recipe> recipes;
    @RequiresApi(api = Build.VERSION_CODES.O)
    void init_recipes() {
        recipes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            recipes.add(new Recipe());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.test, container, false);

        init_recipes();
        RecyclerView recyclerView = rootView.findViewById(R.id.test_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new SummaryAdapter(getContext(), recipes));
        return rootView;
    }
}
