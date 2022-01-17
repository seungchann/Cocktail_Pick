package com.example.cocktail_pick.HomeTab.DetailRecipe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.BaseReceive;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.Recipe;
import com.example.cocktail_pick.RecipeReceive;

import java.lang.reflect.Array;
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
    boolean ONZ_FLAG;
    final float ONZ_ML = (float) 29.5735;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);

        Intent intent = getIntent();
        RecipeReceive recipe = (RecipeReceive) intent.getSerializableExtra("recipe");

        init_step();
        ONZ_FLAG = true;

        recyclerView = findViewById(R.id.step_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StepAdapter(this, steps));

        cocktail_name = findViewById(R.id.detail_cocktail_name);
        comment = findViewById(R.id.detail_comment);
        heart_num = findViewById(R.id.heart_num);
        base = findViewById(R.id.detail_base);
        base_onz = findViewById(R.id.detail_base_onz);
        liqueur = findViewById(R.id.detail_liqueur);
        liqueur_onz = findViewById(R.id.detail_liqueur_onz);
        etc = findViewById(R.id.detail_etc);
        etc_onz = findViewById(R.id.detail_etc_onz);
        garnish = findViewById(R.id.detail_garnish);
        LinearLayout tag1 = findViewById(R.id.detail_first_tag);
        LinearLayout tag2 = findViewById(R.id.detail_second_tag);
        favorite_btn = findViewById(R.id.heart_btn);
        onz_btn = findViewById(R.id.onz_btn);
        ml_btn = findViewById(R.id.ml_btn);
        comment.setMovementMethod(new ScrollingMovementMethod());

        ImageView tag1_circle = tag1.findViewById(R.id.tag_circle_small);
        TextView tag1_text = tag1.findViewById(R.id.tag_text_small);
        ImageView tag2_circle = tag2.findViewById(R.id.tag_circle_small);
        TextView tag2_text = tag2.findViewById(R.id.tag_text_small);

        ArrayList<Integer> tags = (ArrayList<Integer>) recipe.getTags();
        for (Integer tag_id : tags) {
            int tag_id_int = tag_id.intValue();

        }

        // 베이스나 리큐르나 엑트가 없는 경우 예외처리. "" 뜨도록
        base_onz.setText(recipe.getBase().getOnz() + " Oz");
        liqueur_onz.setText(recipe.getLiqueur().getOnz() + " Oz");
        etc_onz.setText(recipe.getEtc().getOnz() + " Oz");

        onz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onz_btn.setBackground(getResources().getDrawable(R.drawable.btn_fill));
                onz_btn.setTextColor(Color.BLACK);
                ml_btn.setBackground(getResources().getDrawable(R.drawable.btn_outline));
                ml_btn.setTextColor(Color.WHITE);

                base_onz.setText(recipe.getBase().getOnz() + " Oz");
                liqueur_onz.setText(recipe.getLiqueur().getOnz() + " Oz");
                etc_onz.setText(recipe.getEtc().getOnz() + " Oz");
            }
        });

        ml_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onz_btn.setBackground(getResources().getDrawable(R.drawable.btn_outline));
                onz_btn.setTextColor(Color.WHITE);
                ml_btn.setBackground(getResources().getDrawable(R.drawable.btn_fill));
                ml_btn.setTextColor(Color.BLACK);

                String format = String.format("%.1f", recipe.getBase().getOnz()*ONZ_ML);
                base_onz.setText(format + " ml");
                format = String.format("%.1f", recipe.getLiqueur().getOnz()*ONZ_ML);
                liqueur_onz.setText(format + " ml");
                format = String.format("%.1f", recipe.getEtc().getOnz()*ONZ_ML);
                etc_onz.setText(format + " ml");
            }
        });

        // profile, name 설정
        cocktail_name.setText(recipe.getCocktailName());
        comment.setText(recipe.getIntro());
        heart_num.setText(recipe.getLike_num() + "");
        base.setText(recipe.getBase().getName());


        liqueur.setText(recipe.getLiqueur().getName());


        etc.setText(recipe.getEtc().getName());


        String garnishString = "";
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
