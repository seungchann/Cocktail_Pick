package com.example.cocktail_pick.RecommendTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.Main.MainViewModel;
import com.example.cocktail_pick.Main.MainViewModelFactory;
import com.example.cocktail_pick.MainRepository;
import com.example.cocktail_pick.Product;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.RetrofitService;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    Context context;
    ArrayList<Product> products, tmp_mine;
    MainViewModel viewModel;
    RetrofitService retrofitService = RetrofitService.Companion.getInstance();
    BottomSheetDialogFragment bottomSheetDialogFragment;
    RecommendViewPagerAdapter adapter;
    public BaseAdapter(Context context, ArrayList<Product> products, BottomSheetDialogFragment bottomSheetDialogFragment, RecommendViewPagerAdapter adapter) {
        this.context = context;
        this.products = products;
        this.bottomSheetDialogFragment = bottomSheetDialogFragment;
        viewModel = new ViewModelProvider((ViewModelStoreOwner) context, new MainViewModelFactory(new MainRepository(retrofitService))).get(MainViewModel.class);
        this.adapter = adapter;
        tmp_mine = (ArrayList<Product>) viewModel.getMyBaseList();
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tmp_mine.add(product);
                viewModel.setMyBaseList(tmp_mine);

                adapter.notifyDataSetChanged();
                bottomSheetDialogFragment.dismiss();
            }
        });
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
