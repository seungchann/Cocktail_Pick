package com.example.cocktail_pick.RecommendTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.Main.MainViewModel;
import com.example.cocktail_pick.Product;
import com.example.cocktail_pick.R;

import java.util.ArrayList;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    Context context;
    ArrayList<Product> products;
    public BaseAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base_small, parent, false);
        BaseAdapter.ViewHolder viewHolder = new BaseAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.profileImage.setImageResource(product.getPicture());
        holder.nameTextView.setText(product.getCompanyName());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView nameTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.base_image_small);
            nameTextView = itemView.findViewById(R.id.base_title_small);
        }
    }
}
