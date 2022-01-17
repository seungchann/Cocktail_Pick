package com.example.cocktail_pick.HomeTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.R;
import com.example.cocktail_pick.Recipe;

import java.util.ArrayList;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.ViewHolder> {

    Context context;
    ArrayList<Recipe> recipes;

    public SummaryAdapter(Context context, ArrayList<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        SummaryAdapter.ViewHolder viewHolder = new SummaryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position*2);
        holder.summary_recipe_name1.setText(recipe.getCocktailName());
//        holder.summary_image1.setImageResource(R.drawable.jack_danial); // TODO
        holder.summary_review1.setText(recipe.getIntro());
        holder.summary_tag_recycler_view1.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
//        holder.summary_tag_recycler_view1.setAdapter(new RecipeTagAdapter(context, recipe.getTags()));

        if (recipes.size()-1 == position*2) {
            holder.item_recipe2.setVisibility(View.INVISIBLE);
            return;
        }

        recipe = recipes.get(position*2+1);
        holder.summary_recipe_name2.setText(recipe.getCocktailName());
//        holder.summary_image2.setImageResource(R.drawable.jack_danial); // TODO
        holder.summary_review2.setText(recipe.getIntro());
        holder.summary_tag_recycler_view2.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
//        holder.summary_tag_recycler_view2.setAdapter(new RecipeTagAdapter(context, recipe.getTags()));
    }

    @Override
    public int getItemCount() {
        return (recipes.size()+1)/2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView summary_recipe_name1, summary_recipe_name2, summary_review1, summary_review2;
        FrameLayout summary_image1, summary_image2;
        RecyclerView summary_tag_recycler_view1, summary_tag_recycler_view2;
        LinearLayout item_recipe1, item_recipe2;


        public ViewHolder(View itemView) {
            super(itemView);
            summary_recipe_name1 = itemView.findViewById(R.id.summary_recipe_name);
            summary_image1 = itemView.findViewById(R.id.firstCustom);
            summary_review1 = itemView.findViewById(R.id.summary_review_text_view);
            summary_tag_recycler_view1 = itemView.findViewById(R.id.summary_tag_recycler_view);
            item_recipe1 = itemView.findViewById(R.id.item_recipe1);

            summary_recipe_name2 = itemView.findViewById(R.id.summary_recipe_name2);
            summary_image2 = itemView.findViewById(R.id.secondCustom);
            summary_review2 = itemView.findViewById(R.id.summary_review_text_view2);
            summary_tag_recycler_view2 = itemView.findViewById(R.id.summary_tag_recycler_view2);
            item_recipe2 = itemView.findViewById(R.id.item_recipe2);
        }
    }
}
