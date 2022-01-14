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

import com.example.cocktail_pick.R;

import java.util.ArrayList;

public class SearchTabFragment extends Fragment {

    public class Tag {
        String taste;
        Color color;

        @RequiresApi(api = Build.VERSION_CODES.O)
        Tag() {
            taste = "달달한";
            color = Color.valueOf(0, 0, 0);
        }
    }

    ArrayList<Tag> tags;
    RecyclerView recyclerView;
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

    void init_tag() {
        tags = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            tags.add(new Tag());
        }

    }
}
