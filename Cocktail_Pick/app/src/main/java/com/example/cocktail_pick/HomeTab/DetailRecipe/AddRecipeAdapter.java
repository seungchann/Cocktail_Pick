package com.example.cocktail_pick.HomeTab.DetailRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.R;

import java.util.ArrayList;

public class AddRecipeAdapter extends RecyclerView.Adapter<AddRecipeAdapter.ViewHolder> {

    Context context;
    ArrayList<EditText> recipes;

    public AddRecipeAdapter(Context context, ArrayList<EditText> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    public void setRecipes(ArrayList<EditText> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_recipe, parent, false);
        AddRecipeAdapter.ViewHolder viewHolder = new AddRecipeAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.order.setText(position + 1 + ". ");
        if (recipes.get(position).getParent() != null) {
            ((ViewGroup)recipes.get(position).getParent()).removeView(recipes.get(position));
        }

        holder.recipe_frame.addView(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView order;
        LinearLayout recipe_frame;

        public ViewHolder(View itemView) {
            super(itemView);
            order = itemView.findViewById(R.id.order_tv);
            recipe_frame = itemView.findViewById(R.id.add_recipe_frame);
        }
    }
}
