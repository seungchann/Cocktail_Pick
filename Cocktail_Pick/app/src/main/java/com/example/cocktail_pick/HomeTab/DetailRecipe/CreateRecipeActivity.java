package com.example.cocktail_pick.HomeTab.DetailRecipe;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.Data.Base;
import com.example.cocktail_pick.HomeTab.CustomDialog;
import com.example.cocktail_pick.HomeTab.CustomHandler;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.Tag;
import com.example.cocktail_pick.databinding.ActivityDetailRecipeBinding;
import com.example.cocktail_pick.databinding.FragmentAddRecipeBinding;
import com.example.cocktail_pick.databinding.ItemCustomImageBinding;
import com.example.cocktail_pick.databinding.ItemTagSmallBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreateRecipeActivity extends AppCompatActivity {

    FragmentAddRecipeBinding binding;
    CustomHandler handler;

    RecyclerView recyclerView;
    ArrayList<String> steps;
    TextView base_oz_ml, liqueur_oz_ml, etc_oz_ml;
    EditText cocktail_name, comment, posting, base_onz, liqueur_onz, etc_onz, liqueur, etc;
    Spinner base;
    Button onz_btn, ml_btn, select_tag, add_complete_btn, add_recipe_btn;
    ItemCustomImageBinding detail_custom;
    String selected_base;
    FrameLayout add_custom_btn;
    boolean ONZ_FLAG = true;
    final float ONZ_ML = (float) 29.5735;
    CustomImage customImage;
    ArrayList<EditText> recipes;
    ArrayList<Tag> selected_tags;
    AddRecipeAdapter addRecipeAdapter;
    ItemTagSmallBinding tag1, tag2;

    public void setSelectedTags(ArrayList<Tag> selected_tags) {
        this.selected_tags = selected_tags;
    }

    public void setTagView() {
        Log.d("############333333", selected_tags.size() + "SIZE!!");
        if (selected_tags.size() == 0) {
            tag1.tagWholeView.setVisibility(View.INVISIBLE);
            tag2.tagWholeView.setVisibility(View.INVISIBLE);
            return;
        } else if (selected_tags.size() == 1) {
            tag2.tagWholeView.setVisibility(View.INVISIBLE);
        }
        tag1.tagWholeView.setVisibility(View.VISIBLE);
        tag1.tagCircleSmall.setColorFilter(Color.parseColor(selected_tags.get(0).getColor()), PorterDuff.Mode.MULTIPLY);
        tag1.tagTextSmall.setText(selected_tags.get(0).getTaste());
        if (selected_tags.size() == 2) {
            tag2.tagWholeView.setVisibility(View.VISIBLE);
            tag2.tagCircleSmall.setColorFilter(Color.parseColor(selected_tags.get(1).getColor()), PorterDuff.Mode.MULTIPLY);
            tag2.tagTextSmall.setText(selected_tags.get(1).getTaste());
        }
    }

    public class CustomImage {
        String glass;
        int ice;
        String garnishFirst;
        String garnishSecond;
        String color;

        CustomImage() {
            glass = "칵테일 글라스";
            ice = 0;
            garnishFirst = "체리";
            garnishSecond = "허브";
            color = "#f9eeba";
        }

        public void setProperty(String glass, int ice, String garnishFirst, String garnishSecond, String color) {
            this.glass = glass;
            this.ice = ice;
            this.garnishFirst = garnishFirst;
            this.garnishSecond = garnishSecond;
            this.color = color;
        }
    }

    public void setImage() {
        handler.setGlass(customImage.glass, customImage.ice, customImage.garnishFirst, customImage.garnishSecond, customImage.color, detail_custom);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentAddRecipeBinding.inflate(getLayoutInflater());
        handler = new CustomHandler();
        setContentView(binding.getRoot());
        Context context = this;

        selected_tags = new ArrayList<>();
        recipes = new ArrayList<>();
        recyclerView = binding.addStepRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addRecipeAdapter = new AddRecipeAdapter(this, recipes);
        recyclerView.setAdapter(addRecipeAdapter);

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
        tag1 = binding.detailFirstTag;
        tag2 = binding.detailSecondTag;
        onz_btn = binding.onzBtn;
        ml_btn = binding.mlBtn;
        comment.setMovementMethod(new ScrollingMovementMethod());
        posting = binding.addPosting;
        detail_custom = binding.addCustom;
        select_tag = binding.addSelectTag;
        add_complete_btn = binding.addCompleteBtn;
        add_custom_btn = binding.addCustomBtn;
        add_recipe_btn = binding.addRecipeBtn;

        tag1.tagWholeView.setVisibility(View.INVISIBLE);
        tag2.tagWholeView.setVisibility(View.INVISIBLE);
        select_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomTagDialog customTagDialog = new CustomTagDialog(context, selected_tags);
                customTagDialog.callDialog();
            }
        });

        add_recipe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = new EditText(context);
                editText.setTextColor(Color.WHITE);
                editText.setHintTextColor(Color.GRAY);
                editText.setHint("레시피를 작성해주세요");
                editText.setGravity(Gravity.BOTTOM);
//                editText.setBackgroundResource(R.color.transparent);
                editText.setBackgroundColor(getResources().getColor(R.color.transparent));
                editText.setSingleLine();
                LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                lparams.gravity = Gravity.BOTTOM;
                editText.setLayoutParams(lparams);
                recipes.add(editText);
                addRecipeAdapter.setRecipes(recipes);
                addRecipeAdapter.notifyDataSetChanged();
            }
        });

        add_custom_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCustomDialog();
            }
        });

        selected_base = Base.values()[0].toString();

        base.setAdapter(new SpinnerAdapter(this));
        base.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_base = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ONZ_FLAG = true;
        onz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onz_btn.setBackground(getResources().getDrawable(R.drawable.btn_fill));
                onz_btn.setTextColor(Color.BLACK);
                ml_btn.setBackground(getResources().getDrawable(R.drawable.btn_outline));
                ml_btn.setTextColor(Color.WHITE);

                liqueur_oz_ml.setText(" oz");
                base_oz_ml.setText(" oz");
                etc_oz_ml.setText(" oz");

                ONZ_FLAG = true;
            }
        });

        ml_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onz_btn.setBackground(getResources().getDrawable(R.drawable.btn_outline));
                onz_btn.setTextColor(Color.WHITE);
                ml_btn.setBackground(getResources().getDrawable(R.drawable.btn_fill));
                ml_btn.setTextColor(Color.BLACK);

                liqueur_oz_ml.setText(" ml");
                base_oz_ml.setText(" ml");
                etc_oz_ml.setText(" ml");

                ONZ_FLAG = false;
            }
        });




        add_complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cocktail_name_string = cocktail_name.getText().toString();
                String comment_string = comment.getText().toString();
                String posting_string = posting.getText().toString();
                float base_onz_float = Float.parseFloat(base_onz.getText().toString());
                float liqueur_onz_float = Float.parseFloat(liqueur_onz.getText().toString());
                float etc_onz_float = Float.parseFloat(etc_onz.getText().toString());

                if (ONZ_FLAG == false) {
                    base_onz_float /= ONZ_ML;
                    liqueur_onz_float /= ONZ_ML;
                    etc_onz_float /= ONZ_ML;
                }

                String liqueur_string = liqueur.getText().toString();
                String etc_string = etc.getText().toString();

                // 아래 두개도 같이 저장하면 됨!
//                selected_base;
//                customImage
//                selected_tags


            }
        });
    }

    private void createCustomDialog() {
        Dialog dialog;
        customImage = new CustomImage();
        dialog = new CustomDialog(this, this.getSupportFragmentManager(), customImage);
        dialog.show();
        dialog.getWindow().setLayout(1000, 1500);

    }
}
