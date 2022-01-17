package com.example.cocktail_pick.HomeTab;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.R;
import com.example.cocktail_pick.Recipe;

import java.util.ArrayList;

public class HomeTabFragment extends Fragment {
    RecyclerView recyclerView;
    ImageView profileImage;
    TextView profileName;
    SummaryAdapter summaryAdapter;
    ArrayList<Recipe> recipes;
    Button testbtn;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        init_recipes();

        profileImage = rootView.findViewById(R.id.profile_image);
        profileName = rootView.findViewById(R.id.profile_text);
        recyclerView = rootView.findViewById(R.id.summary_recycler_view);
        summaryAdapter = new SummaryAdapter(getActivity(), recipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(summaryAdapter);

        testbtn = rootView.findViewById(R.id.testBtn);
        testbtn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                createCustomDialog();
            }
        });

        return rootView;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void init_recipes() {
        recipes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
//            recipes.add(new Recipe());
        }

    }

    private void createCustomDialog() {
        Dialog dialog;
        dialog = new Dialog(getContext());
        dialog.show();
        dialog.getWindow().setLayout(750, 650);
    }
}
