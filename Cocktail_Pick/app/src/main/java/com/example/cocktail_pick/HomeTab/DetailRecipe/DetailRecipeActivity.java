package com.example.cocktail_pick.HomeTab.DetailRecipe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.BaseReceive;
import com.example.cocktail_pick.HomeTab.CustomHandler;
import com.example.cocktail_pick.Main.MainViewModel;
import com.example.cocktail_pick.Main.MainViewModelFactory;
import com.example.cocktail_pick.MainRepository;
import com.example.cocktail_pick.Member;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.Recipe;
import com.example.cocktail_pick.RecipeReceive;
import com.example.cocktail_pick.RetrofitService;
import com.example.cocktail_pick.UserReceive;
import com.example.cocktail_pick.databinding.ActivityDetailRecipeBinding;
import com.example.cocktail_pick.databinding.ItemCustomImageBinding;
import com.example.cocktail_pick.databinding.ItemTagSmallBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DetailRecipeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String[] steps;
    ImageView profile;
    TextView name, cocktail_name, comment, heart_num, posting;
    TextView base, base_onz, liqueur, liqueur_onz, etc, etc_onz, garnish, juice, juice_onz;
    // FrameLayout? cocktail image, tag1, tag2
    ImageButton favorite_btn;
    Button onz_btn, ml_btn;
    ItemCustomImageBinding detail_custom;
    boolean ONZ_FLAG;
    final float ONZ_ML = (float) 29.5735;

    MainViewModel viewModel;
    RetrofitService retrofitService = RetrofitService.Companion.getInstance();

    ActivityDetailRecipeBinding binding;
    CustomHandler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailRecipeBinding.inflate(getLayoutInflater());
        handler = new CustomHandler();

        viewModel = new ViewModelProvider(this, new MainViewModelFactory(new MainRepository(retrofitService))).get(MainViewModel.class);
        viewModel.loadTagBasedRecipe();

        Intent intent = getIntent();
        RecipeReceive recipe = (RecipeReceive) intent.getSerializableExtra("recipe");

        init_step(recipe);
        ONZ_FLAG = true;

        recyclerView = binding.stepRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StepAdapter(this, steps));

        name = binding.detailName;
        profile = binding.detailProfile;
        cocktail_name = binding.detailCocktailName;
        comment = binding.detailComment;
        heart_num = binding.heartNum;
        base = binding.detailBase;
        base_onz = binding.detailBaseOnz;
        liqueur = binding.detailLiqueur;
        liqueur_onz = binding.detailLiqueurOnz;
        etc = binding.detailEtc;
        etc_onz = binding.detailEtcOnz;
        garnish = binding.detailGarnish;
        juice = binding.detailJuice;
        juice_onz = binding.detailJuiceOnz;
        ItemTagSmallBinding tag1 = binding.detailFirstTag;
        ItemTagSmallBinding tag2 = binding.detailSecondTag;
        favorite_btn = (ImageButton) binding.heartBtn;
        onz_btn = binding.onzBtn;
        ml_btn = binding.mlBtn;
        comment.setMovementMethod(new ScrollingMovementMethod());
        posting = binding.detailPosting;
        detail_custom = binding.detailCustom;

        favorite_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_favorite_24, getApplicationContext().getTheme()));

        for (List<String> prefer_user : recipe.getPrefer_user_lists()) {
            String name = prefer_user.get(0);
            String profile_url = prefer_user.get(1);

            viewModel.getCurrentUser().observe(this, new Observer<List<UserReceive>>() {
                @Override
                public void onChanged(List<UserReceive> userReceives) {
                    // email로 해야 맞을듯하지만..
                    if (userReceives.get(0).getProfileURL().equals(profile_url)) {
                        favorite_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_favorite_24, getApplicationContext().getTheme()));
                    } else {
                        Log.d("##########", "Click!!!");
                        favorite_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_favorite_border_24, getApplicationContext().getTheme()));
                    }
                }
            });

        }

        favorite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favorite_btn.getDrawable().equals(getResources().getDrawable(R.drawable.ic_baseline_favorite_border_24, getApplicationContext().getTheme()))) {
                    favorite_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_favorite_border_24, getApplicationContext().getTheme()));
                    // TODO 좋아요 디비에 반영
                } else {
                    favorite_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_favorite_border_24, getApplicationContext().getTheme()));
                    // TODO 좋아요 취소 디비에 반영
                }
            }
        });
//        name.setText();
//        profile. glide..

        ImageView tag1_circle = tag1.tagCircleSmall;
        TextView tag1_text = tag1.tagTextSmall;
        ImageView tag2_circle = tag2.tagCircleSmall;
        TextView tag2_text = tag2.tagTextSmall;

        List<Integer> tags_int = recipe.getTags();
        if (tags_int.size() == 0) {
            tag1.tagWholeView.setVisibility(View.INVISIBLE);
            tag2.tagWholeView.setVisibility(View.INVISIBLE);
        } else if (tags_int.size() == 1) {
            tag2.tagWholeView.setVisibility(View.INVISIBLE);
        }
        // TODO tags_int.get(0) 의 id로 찾아와서 set
         if (tags_int.size() == 2) {
             // tags_int.get(1) 의 id로 set
         }

        handler.setGlass(recipe.getGlass(), recipe.getIce(), recipe.getGarnishFirst(), recipe.getGarnishSecond(), "#F9EEBA", detail_custom);

        ArrayList<Integer> tags = (ArrayList<Integer>) recipe.getTags();
        for (Integer tag_id : tags) {
            int tag_id_int = tag_id.intValue();

        }

        // 베이스나 리큐르나 엑트가 없는 경우 예외처리. "" 뜨도록
        base_onz.setText(recipe.getBaseOz() + " Oz");
        liqueur_onz.setText(recipe.getLiqueurOz() + " Oz");
        etc_onz.setText(recipe.getEtcOz() + " Oz");
        juice_onz.setText(recipe.getJuiceOz() + " Oz");
        posting.setText(recipe.getPosting());


        onz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onz_btn.setBackground(getResources().getDrawable(R.drawable.btn_fill));
                onz_btn.setTextColor(Color.BLACK);
                ml_btn.setBackground(getResources().getDrawable(R.drawable.btn_outline));
                ml_btn.setTextColor(Color.WHITE);

                base_onz.setText(recipe.getBaseOz() + " Oz");
                liqueur_onz.setText(recipe.getLiqueurOz() + " Oz");
                etc_onz.setText(recipe.getEtcOz() + " Oz");
                juice_onz.setText(recipe.getJuiceOz() + " Oz");
            }
        });

        favorite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ml_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onz_btn.setBackground(getResources().getDrawable(R.drawable.btn_outline));
                onz_btn.setTextColor(Color.WHITE);
                ml_btn.setBackground(getResources().getDrawable(R.drawable.btn_fill));
                ml_btn.setTextColor(Color.BLACK);

                String format = String.format("%.1f", recipe.getBaseOz()*ONZ_ML);
                base_onz.setText(format + " ml");
                format = String.format("%.1f", recipe.getLiqueurOz()*ONZ_ML);
                liqueur_onz.setText(format + " ml");
                format = String.format("%.1f", recipe.getEtcOz()*ONZ_ML);
                etc_onz.setText(format + " ml");
                format = String.format("%.1f", recipe.getJuiceOz()*ONZ_ML);
                juice_onz.setText(format + " ml");
            }
        });

        // profile, name 설정
        cocktail_name.setText(recipe.getCocktailName());
        comment.setText(recipe.getIntro());
        heart_num.setText(recipe.getLike_num() + "");
        base.setText(recipe.getBase());
        if (recipe.getLiqueur() == "") {
            binding.liqueurRow.setVisibility(View.INVISIBLE);
        }
        if (recipe.getJuice() == "") {
            binding.juiceRow.setVisibility(View.INVISIBLE);
        }
        if (recipe.getEtc() == "") {
            binding.etcRow.setVisibility(View.INVISIBLE);
        }
        liqueur.setText(recipe.getLiqueur());
        etc.setText(recipe.getEtc());
        juice.setText(recipe.getJuice());


        String garnishString = "";
        if (recipe.getGarnishSecond() == "") garnishString = recipe.getGarnishFirst();
        else garnishString = recipe.getGarnishFirst() + " / " + recipe.getGarnishSecond();
        garnish.setText(garnishString);

        setContentView(binding.getRoot());
    }


    void init_step(RecipeReceive recipe) {
        String recipe_all = recipe.getStep();
        steps = recipe_all.split("\n");
    }

}
