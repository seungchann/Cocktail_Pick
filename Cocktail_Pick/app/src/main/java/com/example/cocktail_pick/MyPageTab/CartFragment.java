package com.example.cocktail_pick.MyPageTab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.HomeTab.SummaryAdapter;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.Recipe;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    ArrayList<Recipe> carts;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage_summary, container, false);

        init_carts();
        recyclerView = rootView.findViewById(R.id.mypage_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(new SummaryAdapter(getActivity(), carts));

        return rootView;
    }

    void init_carts() {
        carts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            carts.add(new Recipe());
        }
    }
}
