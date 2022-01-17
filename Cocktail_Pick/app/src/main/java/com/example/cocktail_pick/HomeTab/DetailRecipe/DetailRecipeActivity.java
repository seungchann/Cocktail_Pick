package com.example.cocktail_pick.HomeTab.DetailRecipe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.BaseReceive;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.Recipe;
import com.example.cocktail_pick.RecipeReceive;

import java.util.ArrayList;

public class DetailRecipeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> steps;
    ImageView profile;
    TextView name, cocktail_name, comment, heart_num;
    TextView base, base_onz, liqueur, liqueur_onz, etc, etc_onz, garnish;
    // FrameLayout? cocktail image, tag1, tag2
    ImageButton favorite_btn;
    Button onz_btn, ml_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);

        Intent intent = getIntent();
        RecipeReceive recipe = (RecipeReceive) intent.getSerializableExtra("recipe");

        init_step();

        recyclerView = findViewById(R.id.step_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StepAdapter(this, steps));

        // profile, name 설정
        cocktail_name.setText(recipe.getCocktailName());
        comment.setText(recipe.getIntro());
        heart_num.setText(recipe.getLike_num() + "");
        base.setText(recipe.getBase().getName());
        base_onz.setText(recipe.getBase().getOnz() + "");

        liqueur.setText(recipe.getLiqueur().getName());
        liqueur_onz.setText(recipe.getLiqueur().getOnz() + "");

        etc.setText(recipe.getEtc().getName());
        etc_onz.setText(recipe.getEtc().getOnz() + "");

        String garnishString = ""
        if (recipe.getGarnishSecond() == "") garnishString = recipe.getGarnishFirst();
        else garnishString = recipe.getGarnishFirst() + " / " + recipe.getGarnishSecond();
        garnish.setText(garnishString);
    }


    void init_step() {
        steps = new ArrayList<>();
        steps.add("텀블러에 보드카와 라임 주스를 넣는다.");
        steps.add("얼음을 넣고 나머지를 진저에일로 채운다.");
        steps.add("라임 슬라이스로 장식하고, 유리막대를 꽂는다.");
    }

}
