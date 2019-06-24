package com.mymovies.bindings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mymovies.R;
import com.mymovies.data.models.Review;
import com.mymovies.data.models.Trailer;
import com.mymovies.databinding.ItemReviewsBinding;
import com.mymovies.databinding.ItemTrailerBinding;
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

    // Gabriel Adjust later this, is taking to much responsibility
    @BindingAdapter("trailers")
    public static void setTrailers(ViewGroup parent, TrailersWidget widget) {
        if (widget != null && !widget.getTrailers().isEmpty()) {
            parent.setVisibility(View.VISIBLE);
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            for (Trailer trailer : widget.getTrailers()) {
                ItemTrailerBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_trailer, parent, false);
                binding.setTitle(trailer.getName());
                View rootView = binding.getRoot();
                rootView.setOnClickListener(v -> widget.getListener().onItemClicked(trailer.getId()));
                parent.addView(rootView);
            }
        } else {
            parent.setVisibility(View.GONE);
        }
    }

    // Gabriel Adjust later this, is taking to much responsibility
    @BindingAdapter("reviews")
    public static void setReviews(ViewGroup parent, ReviewsWidget widget) {
        if (widget != null && !widget.getReviews().isEmpty()) {
            parent.setVisibility(View.VISIBLE);
            Context context = parent.getContext();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            for (Review review : widget.getReviews()) {
                ItemReviewsBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_reviews, parent, false);
                binding.setAuthor(context.getString(R.string.app_author, review.getAuthor()));
                binding.setContent(review.getContent());
                parent.addView(binding.getRoot());
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
