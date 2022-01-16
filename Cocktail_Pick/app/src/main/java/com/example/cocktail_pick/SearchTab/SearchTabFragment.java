package com.example.cocktail_pick.SearchTab;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.cocktail_pick.Tag;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class SearchTabFragment extends Fragment {
    private CustomSuggestionsAdapter customSuggestionsAdapter;
    private MaterialSearchBar searchBar;
    private List<Product> suggestions = new ArrayList<>();

    private final String[] products = {
            "Simvastatin",
            "Carrot Daucus carota",
            "Sodium Fluoride",
            "White Kidney Beans",
            "Salicylic Acid",
            "cetirizine hydrochloride",
            "Mucor racemosus",
            "Thymol",
            "TOLNAFTATE",
            "Albumin Human"
    };

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

        searchBar = rootView.findViewById(R.id.search_bar);
        customSuggestionsAdapter = new CustomSuggestionsAdapter(inflater);
        customSuggestionsAdapter.setSuggestions(suggestions);
        searchBar.setCustomSuggestionAdapter(customSuggestionsAdapter);
        customSuggestionsAdapter.addSuggestion(new Product("product","TEST"));

        for (int i = 1; i < 11; i++) {
            suggestions.add(new Product(products[i - 1], "TEST"));
        }

        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("LOG_TAG", getClass().getSimpleName() + " text changed " + searchBar.getText());
                // send the entered text to our filter and let it manage everything
                customSuggestionsAdapter.getFilter().filter(searchBar.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void init_tag() {
        tags = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
//            tags.add(new Tag());
        }

    }
}
