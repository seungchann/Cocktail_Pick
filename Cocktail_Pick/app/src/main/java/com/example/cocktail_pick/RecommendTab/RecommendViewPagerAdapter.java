package com.example.cocktail_pick.RecommendTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.Data.Cocktail;
import com.example.cocktail_pick.Product;
import com.example.cocktail_pick.R;

import java.util.ArrayList;

public class RecommendViewPagerAdapter extends RecyclerView.Adapter<RecommendViewPagerAdapter.ViewHolderPage> {

    Context context;
    ArrayList<Product> cocktails;
    public boolean cancel_flag = false;

    public RecommendViewPagerAdapter(Context context, ArrayList<Product> cocktails) {
        this.context = context;
        this.cocktails = cocktails;
    }
    @NonNull
    @Override
    public ViewHolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bought_view_pager, parent, false);
        return new ViewHolderPage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPage holder, int position) {
        position *= 6;
        int i = 0;
        for (i = 0; i < 6; i++) {
            int this_pos = position + i;
            if (cocktails.size() == this_pos) break;
            Product cocktail = cocktails.get(this_pos);

            holder.images[i].setImageResource(cocktail.getPicture());
            holder.texts[i].setText(cocktail.getCompanyName());

            holder.cancel_view[i].setVisibility(View.INVISIBLE);
            holder.item_buys[i].clearAnimation();
            if (cancel_flag) {
                holder.cancel_view[i].setVisibility(View.VISIBLE);
                Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
                holder.item_buys[i].startAnimation(shake);
            }

            holder.cancel_btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO DB에서도 삭제 필요
                    cocktails.remove(this_pos);
                    cancel_flag = false;
                    notifyDataSetChanged();
                }
            });

        }

        if (i != 6) {
            for (; i < 6; i++) {
                if (holder.item_buys[i].getVisibility() == View.VISIBLE)
                    holder.item_buys[i].setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return (cocktails.size()+5)/6;
    }

    class ViewHolderPage extends RecyclerView.ViewHolder{

        ImageView[] images = new ImageView[6];
        TextView[] texts = new TextView[6];
        LinearLayout[] item_buys = new LinearLayout[6];
        ImageButton[] cancel_btn = new ImageButton[6];
        CardView[] cancel_view =  new CardView[6];

        public ViewHolderPage(@NonNull View itemView) {
            super(itemView);

            for (int i = 0; i < 6; i++) {
                images[i] = (ImageView) itemView.findViewById(context.getResources().getIdentifier("buy_image" + (i+1), "id", context.getPackageName()));
                texts[i] = (TextView) itemView.findViewById(context.getResources().getIdentifier("buy_text" + (i+1), "id", context.getPackageName()));
                item_buys[i] = (LinearLayout) itemView.findViewById(context.getResources().getIdentifier("item_buy" + (i+1), "id", context.getPackageName()));
                cancel_btn[i] = (ImageButton) itemView.findViewById(context.getResources().getIdentifier("cancel_button" + (i+1), "id", context.getPackageName()));
                cancel_view[i] = (CardView) itemView.findViewById(context.getResources().getIdentifier("cancel" + (i+1), "id", context.getPackageName()));
            }
        }
    }
}
