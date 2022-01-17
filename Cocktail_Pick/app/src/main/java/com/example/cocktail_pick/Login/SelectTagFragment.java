package com.example.cocktail_pick.Login;

import android.graphics.Outline;
import android.media.Image;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.R;

public class SelectTagFragment extends Fragment {

    RecyclerView recyclerView;
    TextView select_email, select_name;
    ImageView select_profile;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_select_tag, container, false);

        select_email = rootView.findViewById(R.id.select_email_text_view);
        select_name = rootView.findViewById(R.id.select_name_text_view);
        select_profile = rootView.findViewById(R.id.select_profile_image_view);

        LinearLayout linearLayout = rootView.findViewById(R.id.select_tag_linear_layout);

        linearLayout.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                float cornerRadiusDP = 16f;
                float cornerRadius = TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, cornerRadiusDP, getResources().getDisplayMetrics());
                outline.setRoundRect(0, 0, view.getWidth(), (int)(view.getHeight() + cornerRadius), cornerRadius);
            }
        });

        recyclerView = rootView.findViewById(R.id.select_tag_recycler_view);

        linearLayout.setClipToOutline(true);
        return rootView;
    }
}
