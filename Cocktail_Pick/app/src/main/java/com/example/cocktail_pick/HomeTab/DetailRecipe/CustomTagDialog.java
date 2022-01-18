package com.example.cocktail_pick.HomeTab.DetailRecipe;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.HomeTab.HomeTabViewModel;
import com.example.cocktail_pick.Login.LoginViewModel;
import com.example.cocktail_pick.Login.SelectTagAdapter;
import com.example.cocktail_pick.Main.MainViewModelFactory;
import com.example.cocktail_pick.MainRepository;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.RetrofitService;
import com.example.cocktail_pick.Tag;

import java.util.ArrayList;
import java.util.List;

public class CustomTagDialog {
    Context context;
    Tag[] selected_tags;
    HomeTabViewModel viewModel;
    RetrofitService retrofitService = RetrofitService.Companion.getInstance();

    public CustomTagDialog(Context context, Tag[] tags) {
        this.context = context;
        selected_tags = tags;
    }

    public void callDialog() {
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.dialog_custom_tag);

        dlg.show();

//        viewModel = new ViewModelProvider((CreateRecipeActivity) context, new MainViewModelFactory(new MainRepository(retrofitService))).get(HomeTabViewModel.class);
//        viewModel.loadTagData();
//        RecyclerView select_tag_recycler_view = dlg.findViewById(R.id.add_tag_recycler_view);
//        select_tag_recycler_view.setLayoutManager(new LinearLayoutManager(context));
//        viewModel.getTagDataList().observe(((CreateRecipeActivity)context).getViewLifecycleOwner(), new Observer<List<Tag>>() {
//            @Override
//            public void onChanged(List<Tag> tags) {
////                Log.d(TAG, tags.get(0).getTaste());
//                select_tag_recycler_view.setAdapter(new SelectTagAdapter(context, (ArrayList<Tag>) tags, selected_tags));
//            }
//        });


    }
}
