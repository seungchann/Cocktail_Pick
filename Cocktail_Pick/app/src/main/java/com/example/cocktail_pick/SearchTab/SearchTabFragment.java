package com.example.cocktail_pick.SearchTab;

import android.graphics.Color;
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

import com.example.cocktail_pick.Data.Tag;
import com.example.cocktail_pick.R;

import java.util.ArrayList;

public class SearchTabFragment extends Fragment {

    ArrayList<Tag> tags;
    RecyclerView recyclerView;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);

        init_tag();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.tag_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new SearchTabAdapter(getActivity(), tags));


        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void init_tag() {
        tags = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            tags.add(new Tag());
        }

    }
}
