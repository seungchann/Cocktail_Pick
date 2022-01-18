package com.example.cocktail_pick.RecommendTab;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cocktail_pick.Data.Cocktail;
import com.example.cocktail_pick.HomeTab.SummaryAdapter;
import com.example.cocktail_pick.Main.MainViewModel;
import com.example.cocktail_pick.Main.MainViewModelFactory;
import com.example.cocktail_pick.MainRepository;
import com.example.cocktail_pick.Product;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.Recipe;
import com.example.cocktail_pick.RetrofitService;

import java.util.ArrayList;

public class RecommendTabFragment extends Fragment {
    ViewPager2 viewPager;
    ArrayList<Product> cocktails, my_cocktails;
    RecommendViewPagerAdapter buying_adapter;
    RecyclerView recommend_recycler_view;
    SummaryAdapter summary_adapter;
    Button addBtn, removeBtn;
    MainViewModel viewModel;
    RetrofitService retrofitService = RetrofitService.Companion.getInstance();

    ArrayList<Recipe> recommendRecipes;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recommend, container, false);

        viewModel = new ViewModelProvider(getActivity(), new MainViewModelFactory(new MainRepository(retrofitService))).get(MainViewModel.class);
        viewModel.initProductList();
        cocktails = (ArrayList<Product>) viewModel.getProductList();
        my_cocktails = (ArrayList<Product>) viewModel.getMyBaseList();
        Context context = getActivity();

        recommend_recycler_view = rootView.findViewById(R.id.recommend_recycler_view);
        recommend_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
//        summary_adapter = new SummaryAdapter(getActivity(), recommendRecipes);
//        recommend_recycler_view.setAdapter(summary_adapter);

        addBtn = rootView.findViewById(R.id.add_buying_btn);
        removeBtn = rootView.findViewById(R.id.remove_buying_btn);

//        for (Product cocktail : cocktails) {
//            for (Product my_cocktail : my_cocktails) {
//                if (cocktail.getCompanyName().equals(my_cocktail.getCompanyName())) {
//                    cocktails.remove(cocktail);
//                }
//            }
//        }

        viewPager = rootView.findViewById(R.id.buy_view_pager);
        buying_adapter = new RecommendViewPagerAdapter(getActivity(), my_cocktails);
        viewPager.setAdapter(buying_adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), cocktails, buying_adapter);
                bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "bottomsheet");
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buying_adapter.cancel_flag = !buying_adapter.cancel_flag;
                buying_adapter.notifyDataSetChanged();
            }
        });


        return rootView;
    }
}
