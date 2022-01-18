package com.example.cocktail_pick.MyPageTab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class CartFragment extends Fragment {

    ArrayList<Recipe> carts;
    RecyclerView recyclerView;
    RetrofitService retrofitService = RetrofitService.Companion.getInstance();
    MainViewModel viewModel;

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
                recyclerView.setAdapter(new SummaryAdapter(getActivity(), (ArrayList<RecipeReceive>) userReceives.get(0).getPreferRecipeList()));
            }
        });


        init_carts();

        return rootView;
    }

    void init_carts() {
        carts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            carts.add(new Recipe());
        }
    }
}
