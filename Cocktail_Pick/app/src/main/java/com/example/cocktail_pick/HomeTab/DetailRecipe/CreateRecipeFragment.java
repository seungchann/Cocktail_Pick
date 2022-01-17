package com.example.cocktail_pick.HomeTab.DetailRecipe;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.HomeTab.CustomHandler;
import com.example.cocktail_pick.databinding.ActivityDetailRecipeBinding;
import com.example.cocktail_pick.databinding.FragmentAddRecipeBinding;
import com.example.cocktail_pick.databinding.ItemCustomImageBinding;
import com.example.cocktail_pick.databinding.ItemTagSmallBinding;

import java.util.ArrayList;

public class CreateRecipeFragment extends Fragment {

    FragmentAddRecipeBinding binding;
    CustomHandler handler;

    RecyclerView recyclerView;
    ArrayList<String> steps;
    TextView base_oz_ml, liqueur_oz_ml, etc_oz_ml;
    EditText cocktail_name, comment, posting, base_onz, liqueur_onz, etc_onz, liqueur, etc;
    Spinner base;
    Button onz_btn, ml_btn, select_tag;
    ItemCustomImageBinding detail_custom;
    boolean ONZ_FLAG;
    final float ONZ_ML = (float) 29.5735;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentAddRecipeBinding.inflate(getLayoutInflater());
        handler = new CustomHandler();

        recyclerView = binding.addStepRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(new StepAdapter(getActivity(), steps));

        base_oz_ml = binding.addBaseOzMl;
        liqueur_oz_ml = binding.addLiqueurOzMl;
        etc_oz_ml = binding.addEtcOzMl;
        cocktail_name = binding.addCocktailName;
        comment = binding.addComment;
        base = binding.addBase;
        base_onz = binding.addBaseOnz;
        liqueur = binding.addLiqueur;
        liqueur_onz = binding.addLiqueurOnz;
        etc = binding.addEtc;
        etc_onz = binding.addEtcOnz;
        ItemTagSmallBinding tag1 = binding.detailFirstTag;
        ItemTagSmallBinding tag2 = binding.detailSecondTag;
        onz_btn = binding.onzBtn;
        ml_btn = binding.mlBtn;
        comment.setMovementMethod(new ScrollingMovementMethod());
        posting = binding.addPosting;
        detail_custom = binding.addCustom;
        select_tag = binding.addSelectTag;
    }
}
