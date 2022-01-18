package com.example.cocktail_pick.HomeTab;

import android.app.Dialog;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.Main.MainViewModel;
import com.example.cocktail_pick.Main.MainViewModelFactory;
import com.example.cocktail_pick.MainRepository;
import com.example.cocktail_pick.Member;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.RecipeReceive;
import com.example.cocktail_pick.RetrofitService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class HomeTabFragment extends Fragment {
    String TAG = "HomeTabFragment";
    RecyclerView recyclerView;
    ImageView profileImage;
    TextView profileName;
    SummaryAdapter summaryAdapter;
    ArrayList<RecipeReceive> recipes;
    Button testbtn;
    MainViewModel viewModel;
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

        init_recipes();

        profileImage = rootView.findViewById(R.id.profile_image);
        profileName = rootView.findViewById(R.id.profile_text);
        recyclerView = rootView.findViewById(R.id.summary_recycler_view);
        viewModel.getTagBasedRecipeList().observe(getViewLifecycleOwner(), new Observer<List<RecipeReceive>>() {
            @Override
            public void onChanged(List<RecipeReceive> recipeReceives) {
                recipes = new ArrayList<RecipeReceive>();
                viewModel.loadUserAccount();
                viewModel.getCurrentUser().observe(getViewLifecycleOwner(), new Observer<List<Member>>() {
                    @Override
                    public void onChanged(List<Member> members) {
                        members.get(0).getTag().forEach(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) {
                                recipeReceives.forEach(new Consumer<RecipeReceive>() {
                                    @Override
                                    public void accept(RecipeReceive recipeReceive) {
                                        List<Integer> tags = recipeReceive.getTags();
                                        tags.forEach(new Consumer<Integer>() {
                                            @Override
                                            public void accept(Integer tagInteger) {
                                                if(integer == tagInteger) {
                                                    recipes.add(recipeReceive);
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        });

                    }
                });

                // 중복 제거
                HashSet<RecipeReceive> distinctRecipes = new HashSet<>(recipes);
                recipes = new ArrayList<>(distinctRecipes);

                summaryAdapter = new SummaryAdapter(getActivity(), recipes);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(summaryAdapter);
            }
        });


        testbtn = rootView.findViewById(R.id.testBtn);
        testbtn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                createCustomDialog();
            }
        });

        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), new Observer<List<Member>>() {
            @Override
            public void onChanged(List<Member> members) {
                Log.d(TAG,members.get(0).getUserName());
            }
        });

        return rootView;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void init_recipes() {
        recipes = new ArrayList<RecipeReceive>();

        for (int i = 0; i < 10; i++) {
//            recipes.add(new Recipe());
        }

    }

    private void createCustomDialog() {
        Dialog dialog;
        dialog = new CustomDialog(requireContext(), getActivity().getSupportFragmentManager());
        dialog.show();
        dialog.getWindow().setLayout(1000, 1500);
    }
}
