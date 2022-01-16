package com.example.cocktail_pick.HomeTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.R;
import com.example.cocktail_pick.Tag;

import java.util.ArrayList;

public class RecipeTagAdapter extends RecyclerView.Adapter<RecipeTagAdapter.ViewHolder> {

    Context context;
    ArrayList<Tag> tags;

    public RecipeTagAdapter(Context context) {
        this.context = context;
        ArrayList<Tag> testTagList = new ArrayList<>();
        this.tags = testTagList;
    }

    public RecipeTagAdapter(Context context, ArrayList<Tag> tags) {
        this.context = context;
        this.tags = tags;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_small, parent, false);
        RecipeTagAdapter.ViewHolder viewHolder = new RecipeTagAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tag tag = tags.get(position);
        holder.tag_color.setImageResource(R.drawable.circle); // TODO
        holder.tag_taste.setText(tag.getTaste());
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tag_taste;
        ImageView tag_color;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tag_color = itemView.findViewById(R.id.tag_circle_small);
            tag_taste = itemView.findViewById(R.id.tag_text_small);
        }
    }
}
