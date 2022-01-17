package com.example.cocktail_pick.Login;

import android.content.Context;
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

import com.example.cocktail_pick.Data.Tag;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.SearchTab.SearchTabAdapter;

import java.util.ArrayList;

public class SelectTagAdapter extends RecyclerView.Adapter<SelectTagAdapter.ViewHolder> {
    Context context;
    ArrayList<Tag> tags;

    public SelectTagAdapter(Context context, ArrayList<Tag> tags) {
        this.context = context;
        this.tags = tags;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_tag, parent, false);
        SelectTagAdapter.ViewHolder viewHolder = new SelectTagAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tag tag = tags.get(position*3);
        holder.tag1.setText(tag.getTaste());
        holder.circle1.setColorFilter(R.color.red, PorterDuff.Mode.MULTIPLY);
        if (tags.size()-1 == position*3) {
            holder.card2.setVisibility(View.INVISIBLE);
            return;
        }

        tag = tags.get(position*3+1);
        holder.tag2.setText(tag.getTaste());
        holder.circle2.setColorFilter(ContextCompat.getColor(context, R.color.red), PorterDuff.Mode.MULTIPLY);
        if (tags.size()-1 == position*3+1) {
            holder.card3.setVisibility(View.INVISIBLE);
            return;
        }

        tag = tags.get(position*3+2);
        holder.tag3.setText(tag.getTaste());
        holder.circle3.setColorFilter(ContextCompat.getColor(context, R.color.green), PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public int getItemCount() {
        return (tags.size() + 2) / 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView circle1, circle2, circle3;
        TextView tag1, tag2, tag3;
        CardView card1, card2, card3;

        public ViewHolder(View itemView) {
            super(itemView);
            circle1 = itemView.findViewById(R.id.circle1);
            circle2 = itemView.findViewById(R.id.circle2);
            circle3 = itemView.findViewById(R.id.circle3);
            tag1 = itemView.findViewById(R.id.tag_btn1);
            tag2 = itemView.findViewById(R.id.tag_btn2);
            tag3 = itemView.findViewById(R.id.tag_btn3);
            card1 = itemView.findViewById(R.id.card1);
            card2 = itemView.findViewById(R.id.card2);
            card3 = itemView.findViewById(R.id.card3);
        }
    }
}
