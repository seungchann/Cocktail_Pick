package com.example.cocktail_pick.Login;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.Main.MainViewModelFactory;
import com.example.cocktail_pick.MainRepository;
import com.example.cocktail_pick.R;
import com.example.cocktail_pick.RetrofitService;
import com.example.cocktail_pick.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SelectTagFragment extends Fragment {

    RecyclerView recyclerView;
    TextView select_email, select_name;
    ImageView select_profile;
    Button select_tag_complete_btn;

    LoginViewModel viewModel;
    RetrofitService retrofitService = RetrofitService.Companion.getInstance();
    String TAG = "SelectTagAdapter";

    ArrayList<Tag> selected_tags;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_select_tag, container, false);
        Context context = getActivity();
        viewModel = new ViewModelProvider(getActivity(), new MainViewModelFactory(new MainRepository(retrofitService))).get(LoginViewModel .class);
        viewModel.loadTagData();
        recyclerView = rootView.findViewById(R.id.select_tag_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewModel.getTagDataList().observe(getViewLifecycleOwner(), new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
//                Log.d(TAG, tags.get(0).getTaste());
                recyclerView.setAdapter(new SelectTagAdapter(getActivity(), (ArrayList<Tag>) tags, selected_tags, false));
            }
        });

        select_email = rootView.findViewById(R.id.select_email_text_view);
        select_name = rootView.findViewById(R.id.select_name_text_view);
        select_profile = rootView.findViewById(R.id.select_profile_image_view);
        select_tag_complete_btn = rootView.findViewById(R.id.select_tag_complete_btn);

        LinearLayout linearLayout = rootView.findViewById(R.id.select_tag_linear_layout);

        linearLayout.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                float cornerRadiusDP = 16f;
                float cornerRadius = TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, cornerRadiusDP, getResources().getDisplayMetrics());
                outline.setRoundRect(0, 0, view.getWidth(), (int)(view.getHeight() + cornerRadius), cornerRadius);
            }
        });
        linearLayout.setClipToOutline(true);

        selected_tags = new ArrayList<>();

        select_tag_complete_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                // selected_tags를 유저 정보에 저장하고 메인액티비티로 넘어가면 됨.
                selected_tags.forEach(new Consumer<Tag>() {
                    @Override
                    public void accept(Tag tag) {
                        viewModel.getCurrentUser().getTag().add(tag.getId());
                    }
                });
                Log.d(TAG, viewModel.currentUser.getTag().get(0).toString());
                viewModel.addMember(viewModel.currentUser);
                ((LoginActivity) getActivity()).moveToMainActivity();
            }
        });

        return rootView;
    }
}
