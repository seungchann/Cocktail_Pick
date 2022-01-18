package com.example.cocktail_pick.SearchTab;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail_pick.HomeTab.HomeTabFragment;
import com.example.cocktail_pick.Main.MainActivity;
import com.example.cocktail_pick.Main.RecipeFragment;
import com.example.cocktail_pick.Product;
import com.example.cocktail_pick.R;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import java.util.ArrayList;
import java.util.Observer;

public class CustomSuggestionsAdapter extends SuggestionsAdapter<Product, CustomSuggestionsAdapter.SuggestionHolder> {

    Context context;

    public CustomSuggestionsAdapter(Context context, LayoutInflater inflater) {
        super(inflater);
        this.context = context;
    }

    @Override
    public int getSingleViewHeight() {
        return 80;
    }

    @Override
    public SuggestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.item_suggestion, parent, false);
        return new SuggestionHolder(view);
    }

    @Override
    public void onBindSuggestionHolder(Product suggestion, SuggestionHolder holder, int position) {
        holder.title.setText(suggestion.getCompanyName());
        holder.subtitle.setText(suggestion.getBase());
        holder.image.setImageResource(suggestion.getPicture());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) context).replaceView(new RecipeFragment());
            }
        });
    }

    /**
     * <b>Override to customize functionality</b>
     * <p>Returns a filter that can be used to constrain data with a filtering
     * pattern.</p>
     * <p>
     * <p>This method is usually implemented by {@link androidx.recyclerview.widget.RecyclerView.Adapter}
     * classes.</p>
     *
     * @return a filter used to constrain data
     */

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                String term = constraint.toString();
                if(term.isEmpty())
                    suggestions = suggestions_clone;
                else {
                    suggestions = new ArrayList<>();
                    for (Product item: suggestions_clone)
                        if(item.getCompanyName().contains(term))
                            suggestions.add(item);
                }
                results.values = suggestions;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                suggestions = (ArrayList<Product>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    static class SuggestionHolder extends RecyclerView.ViewHolder{
        protected TextView title;
        protected TextView subtitle;
        protected ImageView image;
        protected Button button;

        public SuggestionHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.suggestion_title);
            subtitle = (TextView) itemView.findViewById(R.id.suggestion_subtitle);
            image  = (ImageView) itemView.findViewById(R.id.suggestion_image);
            button = (Button) itemView.findViewById(R.id.suggestion_btn);
        }
    }

}
