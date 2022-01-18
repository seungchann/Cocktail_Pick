package com.example.cocktail_pick.SearchTab;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.Main.MainActivity;
import com.example.cocktail_pick.Main.RecipeFragment;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.Tag;

import java.util.ArrayList;

public class SearchTabAdapter extends RecyclerView.Adapter<SearchTabAdapter.ViewHolder> {

    ArrayList<Tag> tags;
    Context context;
    public SearchTabAdapter(Context context, ArrayList<Tag> tags) {
        this.tags = tags;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // holder.circle1

        Tag tag = tags.get(position*2);
        holder.tag1.setText(tag.getTaste());
        holder.circle1.setColorFilter(Color.parseColor(tag.getColor()), PorterDuff.Mode.MULTIPLY);
        if (tags.size()-1 == position*2) {
            holder.card2.setVisibility(View.INVISIBLE);
            return;
        }

        tag = tags.get(position*2+1);
        //holder.circle1.set
        holder.tag2.setText(tag.getTaste());
        holder.circle2.setColorFilter(Color.parseColor(tag.getColor()), PorterDuff.Mode.MULTIPLY);

        holder.tag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) context).replaceView(new RecipeFragment(2));
            }
        });
    }

    @Override
    public int getItemCount() {
        return (tags.size()+1)/2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView circle1, circle2;
        TextView tag1, tag2;
        CardView card1, card2;

        public ViewHolder(View itemView) {
            super(itemView);
            circle1 = itemView.findViewById(R.id.circle1);
            circle2 = itemView.findViewById(R.id.circle2);
            tag1 = itemView.findViewById(R.id.tag_btn1);
            tag2 = itemView.findViewById(R.id.tag_btn2);
            card1 = itemView.findViewById(R.id.card1);
            card2 = itemView.findViewById(R.id.card2);
        }
    }
}
