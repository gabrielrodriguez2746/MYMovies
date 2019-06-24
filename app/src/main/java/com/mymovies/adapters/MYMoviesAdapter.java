package com.mymovies.adapters;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mymovies.R;
import com.mymovies.data.models.Movie;
import com.mymovies.databinding.ItemMovieBinding;

import java.util.Objects;

public class MYMoviesAdapter extends PagedListAdapter<Movie, MYMoviesAdapter.MYMoviesViewHolder> {

    // Gabriel This should be provided as a factory
    private ViewGroup.LayoutParams defaultLayoutParameters;
    private MYMoviesAdapter.OnMovieClicked listener;

    private static MyMoviesDiffResolver diffResolver = new MyMoviesDiffResolver();


    public MYMoviesAdapter(DisplayMetrics displayMetrics, MYMoviesAdapter.OnMovieClicked listener) {
        super(diffResolver);
        this.listener = listener;
        int height = (int) (Math.max(displayMetrics.heightPixels, displayMetrics.widthPixels) * 0.43);
        defaultLayoutParameters = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
    }

    @NonNull
    @Override
    public MYMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMovieBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_movie, parent, false);
        View rootView = binding.getRoot();
        rootView.setLayoutParams(defaultLayoutParameters);
        return new MYMoviesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MYMoviesViewHolder holder, int position) {
        holder.bind(Objects.requireNonNull(getItem(position)));
    }

    class MYMoviesViewHolder extends RecyclerView.ViewHolder {

        private ItemMovieBinding binding;

        MYMoviesViewHolder(@NonNull ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Movie data) {
            binding.setImageUrl(data.getPosterPath());
            binding.setTitle(data.getTitle());
            binding.getRoot().setOnClickListener(v -> listener.onMovieClicked(data.getId()));
        }
    }

    static class MyMoviesDiffResolver extends DiffUtil.ItemCallback<Movie> {

        MyMoviesDiffResolver() {
        }

        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.hashCode() == newItem.hashCode();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem); // Gabriel Fix this with real logic
        }
    }

    public interface OnMovieClicked {

        void onMovieClicked(int movieId);
    }
}
