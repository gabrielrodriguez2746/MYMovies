package com.mymovies.bindings;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.mymovies.models.RecyclerViewConfiguration;

public class GeneralBindings {

    @BindingAdapter("configuration")
    public static void setReyclerViewConfiguration(RecyclerView recyclerView, RecyclerViewConfiguration configuration) {
        recyclerView.setLayoutManager(configuration.getLayoutManager());
        recyclerView.setAdapter(configuration.getAdapter());
        recyclerView.setNestedScrollingEnabled(configuration.isNestedScroll());
        recyclerView.setHasFixedSize(configuration.isHasFixedSize());
        RecyclerView.ItemDecoration decorator = configuration.getDecorator();
        if (decorator != null) {
            recyclerView.addItemDecoration(decorator);
        }
    }

}
