package com.mymovies;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MYMoviesAdapter extends ListAdapter<String, MYMoviesAdapter.MYMoviesViewHolder> {

    private static MyMoviesDiffResolver diffResolver = new MyMoviesDiffResolver();
    private static ViewGroup.LayoutParams defaultLayoutParameters =
            new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

    public MYMoviesAdapter() {
        super(diffResolver);
    }

    @NonNull
    @Override
    public MYMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        textView.setLayoutParams(defaultLayoutParameters);
        return new MYMoviesViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull MYMoviesViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class MYMoviesViewHolder extends RecyclerView.ViewHolder {

        private TextView view;

        MYMoviesViewHolder(@NonNull TextView itemView) {
            super(itemView);
            view = itemView;
        }

        void bind(String data) {
            view.setText(data);
        }
    }

    static class MyMoviesDiffResolver extends DiffUtil.ItemCallback<String> {

        MyMoviesDiffResolver() {
        }

        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.hashCode() == newItem.hashCode();
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem); // TODO Fix this with real logic
        }
    }

}
