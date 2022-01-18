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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.Main.MainActivity;
import com.example.cocktail_pick.Main.MainViewModel;
import com.example.cocktail_pick.Main.MainViewModelFactory;
import com.example.cocktail_pick.Main.RecipeFragment;
import com.example.cocktail_pick.MainRepository;
import com.example.cocktail_pick.Product;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.RecipeReceive;
import com.example.cocktail_pick.RetrofitService;
import com.example.cocktail_pick.Tag;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.example.cocktail_pick.Data.Base;

import java.util.ArrayList;
import java.util.List;

public class SearchTabFragment extends Fragment {
    private CustomSuggestionsAdapter customSuggestionsAdapter;
    private MaterialSearchBar searchBar;
    private List<Product> suggestions = new ArrayList<>();

    MainViewModel viewModel;
    RetrofitService retrofitService = RetrofitService.Companion.getInstance();

    ArrayList<Tag> tags;
    RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);
        viewModel = new ViewModelProvider(getActivity(), new MainViewModelFactory(new MainRepository(retrofitService))).get(MainViewModel.class);

        init_tag();
        viewModel.initProductList();
        viewModel.loadTagData();

        suggestions = (ArrayList<Product>) viewModel.getProductList();

        viewModel.getTagDataList().observe(getViewLifecycleOwner(), new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                recyclerView = (RecyclerView) rootView.findViewById(R.id.tag_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(new SearchTabAdapter(getActivity(), (ArrayList<Tag>)tags));
            }
        });


        searchBar = rootView.findViewById(R.id.search_bar);
        customSuggestionsAdapter = new CustomSuggestionsAdapter(getActivity(), inflater);
        customSuggestionsAdapter.setSuggestions(suggestions);
        searchBar.setCustomSuggestionAdapter(customSuggestionsAdapter);


        rootView.findViewById(R.id.base1_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.loadBaseBasedRecipe("진");
                viewModel.getBaseBasedRecipeList().observe(getViewLifecycleOwner(), new Observer<List<RecipeReceive>>() {
                    @Override
                    public void onChanged(List<RecipeReceive> recipeReceives) {
                        ((MainActivity) getContext()).replaceView(new RecipeFragment());
                    }
                });

            }
        });


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
