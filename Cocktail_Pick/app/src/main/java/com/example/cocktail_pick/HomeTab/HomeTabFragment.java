package com.example.cocktail_pick.HomeTab;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.HomeTab.DetailRecipe.CreateRecipeActivity;
import com.example.cocktail_pick.Main.MainViewModel;
import com.example.cocktail_pick.Main.MainViewModelFactory;
import com.example.cocktail_pick.MainRepository;
import com.example.cocktail_pick.Member;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.Recipe;
import com.example.cocktail_pick.RecipeReceive;
import com.example.cocktail_pick.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeTabFragment extends Fragment {
    String TAG = "HomeTabFragment";
    RecyclerView recyclerView;
    ImageView profileImage;
    TextView profileName;
    SummaryAdapter summaryAdapter;
    ArrayList<Recipe> recipes;
    MainViewModel viewModel;
    FloatingActionButton fab;
    RetrofitService retrofitService = RetrofitService.Companion.getInstance();

    String glass;
    String garnishFirst;
    String garnishSecond;
    Integer ice;
    String color;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        viewModel = new ViewModelProvider(getActivity(), new MainViewModelFactory(new MainRepository(retrofitService))).get(MainViewModel.class);
        viewModel.loadTagBasedRecipe();

        fab = rootView.findViewById(R.id.fab);
        profileImage = rootView.findViewById(R.id.profile_image);
        profileName = rootView.findViewById(R.id.profile_text);
        recyclerView = rootView.findViewById(R.id.summary_recycler_view);
        viewModel.getTagBasedRecipeList().observe(getViewLifecycleOwner(), new Observer<List<RecipeReceive>>() {
            @Override
            public void onChanged(List<RecipeReceive> recipeReceives) {
                summaryAdapter = new SummaryAdapter(getActivity(), (ArrayList<RecipeReceive>) recipeReceives);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(summaryAdapter);
            }
        });


//        testbtn = rootView.findViewById(R.id.testBtn);
//
//        List<Integer> tagstest = new ArrayList<Integer>(Arrays.asList(5, 6));
//        Recipe test = new Recipe(3
//                , "이건 테스트"
//                ,"테스트 칵테일"
//                , "칵테일 글라스"
//                , 3
//                , "체리"
//                , "자몽"
//                , "#f9eeba"
//                , "이건 글이에요"
//                , tagstest
//                , "위스키"
//                , 3.0f
//                , "오렌지 쥬스"
//                , 3
//                , "리퀴르"
//                , 2
//                , "예시"
//                , 2
//                , "스텝"
//        );

//        testbtn.setOnClickListener(new Button.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                viewModel.addRecipe(test);
//                viewModel.getRecipePost().observe(getViewLifecycleOwner(), new Observer<Recipe>() {
//
//                    @Override
//                    public void onChanged(Recipe recipe) {
//
//                    }
//                });
//
//        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateRecipeActivity.class);
                startActivity(intent);
            }
        });

        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), new Observer<List<Member>>() {
            @Override
            public void onChanged(List<Member> members) {
                Log.d(TAG, members.get(0).getUserName());
            }
        });

        return rootView;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void init_recipes() {
        recipes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
//            recipes.add(new Recipe());
        }

    }
}
