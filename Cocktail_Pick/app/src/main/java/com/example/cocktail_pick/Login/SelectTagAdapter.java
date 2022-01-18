package com.example.cocktail_pick.Login;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.R;
import com.example.cocktail_pick.Tag;

import java.util.ArrayList;

public class SelectTagAdapter extends RecyclerView.Adapter<SelectTagAdapter.ViewHolder> {
    Context context;
    ArrayList<Tag> tags, selected_tags;
    boolean flag;

    public SelectTagAdapter(Context context, ArrayList<Tag> tags, ArrayList<Tag> selected_tags, boolean flag) {
        this.context = context;
        this.tags = tags;
        this.selected_tags = selected_tags;
        this.flag = flag;

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
        final Tag tag = tags.get(position*3);
        holder.tag1.setText(tag.getTaste());
        holder.circle1.setColorFilter(Color.parseColor(tag.getColor()), PorterDuff.Mode.MULTIPLY);

        holder.tag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected_tags.contains(tag)) {
                    selected_tags.remove(tag);
                    holder.card1.setCardBackgroundColor(context.getResources().getColor(R.color.gray));
                } else {
                    int max_tag_num = 3;
                    if (flag) max_tag_num = 2;
                    if (selected_tags.size() == max_tag_num) {
                        Toast.makeText(context, "최대 " + max_tag_num + "개까지 태그를 선택할 수 있습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    selected_tags.add(tag);
                    holder.card1.setCardBackgroundColor(context.getResources().getColor(R.color.selected_tag));
                }
            }
        });

        if (tags.size()-1 == position*3) {
            holder.card2.setVisibility(View.INVISIBLE);
            holder.card3.setVisibility(View.INVISIBLE);
            return;
        }

        final Tag tag2 = tags.get(position*3+1);
        holder.tag2.setText(tag2.getTaste());
        holder.circle2.setColorFilter(Color.parseColor(tag2.getColor()), PorterDuff.Mode.MULTIPLY);

        holder.tag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected_tags.contains(tag2)) {
                    selected_tags.remove(tag2);
                    holder.card2.setCardBackgroundColor(context.getResources().getColor(R.color.gray));
                } else {
                    int max_tag_num = 3;
                    if (flag) max_tag_num = 2;
                    if (selected_tags.size() == max_tag_num) {
                        Toast.makeText(context, "최대 " + max_tag_num + "개까지 태그를 선택할 수 있습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    selected_tags.add(tag2);
                    holder.card2.setCardBackgroundColor(context.getResources().getColor(R.color.selected_tag));
                }
            }
        });

        if (tags.size()-1 == position*3+1) {
            holder.card3.setVisibility(View.INVISIBLE);
            return;
        }

        final Tag tag3 = tags.get(position*3+2);
        holder.tag3.setText(tag3.getTaste());
        holder.circle3.setColorFilter(Color.parseColor(tag3.getColor()), PorterDuff.Mode.MULTIPLY);

        holder.tag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected_tags.contains(tag3)) {
                    selected_tags.remove(tag3);
                    holder.card3.setCardBackgroundColor(context.getResources().getColor(R.color.gray));
                } else {
                    selected_tags.add(tag3);
                    holder.card3.setCardBackgroundColor(context.getResources().getColor(R.color.selected_tag));
                }            }
        });
    }

    @Override
    public int getItemCount() {
        return (tags.size() + 2) / 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView circle1, circle2, circle3;
        Button tag1, tag2, tag3;
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
