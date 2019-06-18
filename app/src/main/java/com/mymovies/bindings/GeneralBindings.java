package com.mymovies.bindings;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.mymovies.data.models.Review;
import com.mymovies.data.models.Trailer;
import com.mymovies.models.RecyclerViewConfiguration;
import com.mymovies.widget.ReviewsWidget;
import com.mymovies.widget.TrailersWidget;
import com.squareup.picasso.Picasso;

public class GeneralBindings {

    @BindingAdapter("configuration")
    public static void setRecyclerViewConfiguration(RecyclerView recyclerView, RecyclerViewConfiguration configuration) {
        recyclerView.setLayoutManager(configuration.getLayoutManager());
        recyclerView.setAdapter(configuration.getAdapter());
        recyclerView.setNestedScrollingEnabled(configuration.isNestedScroll());
        recyclerView.setHasFixedSize(configuration.isHasFixedSize());
        RecyclerView.ItemDecoration decorator = configuration.getDecorator();
        if (decorator != null) {
            recyclerView.addItemDecoration(decorator);
        }
    }

    // TODO Adjust later this, is taking to much responsibility
    @BindingAdapter("trailers")
    public static void setTrailers(ViewGroup parent, TrailersWidget widget) {
        if (widget != null && !widget.getTrailers().isEmpty()) {
            parent.setVisibility(View.VISIBLE);
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            for (Trailer trailer : widget.getTrailers()) {
                TextView textView = new TextView(parent.getContext());
                textView.setLayoutParams(params);
                textView.setText(trailer.getName());
                textView.setOnClickListener(v -> widget.getListener().onItemClicked(trailer.getId()));
                parent.addView(textView);
            }
        } else {
            parent.setVisibility(View.GONE);
        }
    }

    // TODO Adjust later this, is taking to much responsibility
    @BindingAdapter("reviews")
    public static void setReviews(ViewGroup parent, ReviewsWidget widget) {
        if (widget != null && !widget.getReviews().isEmpty()) {
            parent.setVisibility(View.VISIBLE);
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            for (Review review : widget.getReviews()) {
                TextView textView = new TextView(parent.getContext());
                textView.setLayoutParams(params);
                textView.setText(review.getContent());
                parent.addView(textView);
            }
        } else {
            parent.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("image_url")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Picasso.get().load(imageUrl)
                .into(imageView);
    }

}
