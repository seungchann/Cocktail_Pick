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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.HomeTab.SummaryAdapter;
import com.example.cocktail_pick.Main.MainViewModel;
import com.example.cocktail_pick.Main.MainViewModelFactory;
import com.example.cocktail_pick.MainRepository;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.Recipe;
import com.example.cocktail_pick.RecipeReceive;
import com.example.cocktail_pick.RetrofitService;
import com.example.cocktail_pick.UserReceive;

import java.util.ArrayList;
import java.util.List;

public class MyRecipeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Recipe> my_recipes;
    RetrofitService retrofitService = RetrofitService.Companion.getInstance();
    MainViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage_summary, container, false);
        viewModel = new ViewModelProvider(getActivity(), new MainViewModelFactory(new MainRepository(retrofitService))).get(MainViewModel.class);
        viewModel.loadCurrentAccount();

        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), new Observer<List<UserReceive>>() {
            @Override
            public void onChanged(List<UserReceive> userReceives) {
                recyclerView = rootView.findViewById(R.id.mypage_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(new SummaryAdapter(getActivity(), (ArrayList<RecipeReceive>) userReceives.get(0).getRecipes()));
            }
        });


        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void init_my_recipes() {
        my_recipes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            my_recipes.add(new Recipe("피치 크러쉬"));
        }
    }
}
