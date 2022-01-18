package com.example.cocktail_pick.HomeTab.DetailRecipe;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.HomeTab.HomeTabViewModel;
import com.example.cocktail_pick.Login.LoginViewModel;
import com.example.cocktail_pick.Login.SelectTagAdapter;
import com.example.cocktail_pick.Main.MainViewModel;
import com.example.cocktail_pick.Main.MainViewModelFactory;
import com.example.cocktail_pick.MainRepository;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.RetrofitService;
import com.example.cocktail_pick.Tag;
import java.util.ArrayList;
import java.util.List;

public class CustomTagDialog {
    Context context;
    ArrayList<Tag> selected_tags, selected_tags_tmp;
    Button complete_btn;
    MainViewModel viewModel;
    RetrofitService retrofitService = RetrofitService.Companion.getInstance();

    public CustomTagDialog(Context context, ArrayList<Tag> tags) {
        this.context = context;
        selected_tags = tags;
    }

    public void callDialog() {
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dlg.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dlg.getWindow().setLayout(450, 500);
        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.dialog_custom_tag);

        dlg.show();

        selected_tags_tmp = new ArrayList<>();
        viewModel = new ViewModelProvider((CreateRecipeActivity) context, new MainViewModelFactory(new MainRepository(retrofitService))).get(MainViewModel.class);
        viewModel.loadTagData();
        RecyclerView select_tag_recycler_view = dlg.findViewById(R.id.add_tag_recycler_view);
        select_tag_recycler_view.setLayoutManager(new LinearLayoutManager(context));
        viewModel.getTagDataList().observe((LifecycleOwner) context, new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                select_tag_recycler_view.setAdapter(new SelectTagAdapter(context, (ArrayList<Tag>) tags, selected_tags_tmp, true));
            }
        });


        complete_btn = dlg.findViewById(R.id.add_tag_complete_btn);
        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                selected_tags = new ArrayList<>();
//                for (Tag tag : selected_tags_tmp) {
//                    selected_tags.add(tag);
//                }

                ((CreateRecipeActivity) context).setSelectedTags(selected_tags_tmp);

                ((CreateRecipeActivity) context).setTagView();
                dlg.dismiss();
            }
        });
    }
}
