package com.mymovies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.mymovies.R;
import com.mymovies.data.models.Movie;
import com.mymovies.databinding.FragmentMovieDetailBinding;
import com.mymovies.viewmodels.DetailMoviesViewModel;
import com.mymovies.viewmodels.PopularMoviesDetailViewModel;
import com.mymovies.viewmodels.TopRatedMoviesDetailViewModel;
import com.mymovies.widget.ReviewsWidget;
import com.mymovies.widget.TrailersWidget;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

import static com.mymovies.helpers.Helpers.watchYoutubeVideo;

public class DetailMovieFragment extends Fragment {

    public static String MOVIE_ID_KEY = "movie_id";
    public static String MOVIE_DETAIL_TYPE = "detail_type";

    private FragmentMovieDetailBinding binding;
    private DetailMoviesViewModel viewModel;

    @Inject
    ViewModelProvider.Factory factory;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false);
        }
        return binding != null ? binding.getRoot() : super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = Objects.requireNonNull(getArguments());
        if (viewModel == null) {
            if (arguments.getInt(MOVIE_DETAIL_TYPE, 0) == 0) {
                viewModel = ViewModelProviders.of(this, factory).get(PopularMoviesDetailViewModel.class);
            } else {
                viewModel = ViewModelProviders.of(this, factory).get(TopRatedMoviesDetailViewModel.class);
            }
        }
        processMovie();
        processTrailers();
        processReviews();

        if (arguments.containsKey(MOVIE_ID_KEY)) {
            viewModel.getMovieFromId(arguments.getInt(MOVIE_ID_KEY));
        }
    }

    private void processMovie() {
        viewModel.getMovieLiveData().observe(getViewLifecycleOwner(), movie -> {
            if (movie != null) {
                // TODO Remove movie information mapping form here
                binding.setOverview(movie.getOverview());
                binding.setTitle(movie.getOriginalTitle());
                binding.setReleaseDate(movie.getReleaseDate());
                binding.setRating(String.valueOf(movie.getPopularity()));
                binding.setBackdropImage(movie.getBackdropPath());
                binding.setPosterImage(movie.getPosterPath());
                adjustToolbarTitle(movie);
            }
        });
    }

    private void processTrailers() {
        viewModel.getTrailersLiveData().observe(getViewLifecycleOwner(), trailers -> {
            if (trailers != null) {
                binding.setTrailers(new TrailersWidget(trailers, id -> watchYoutubeVideo(Objects.requireNonNull(getContext()), id)));

            }
        });
    }

    private void processReviews() {
        viewModel.getReviewsLiveData().observe(getViewLifecycleOwner(), reviews -> {
            if (reviews != null) {
                binding.setReviews(new ReviewsWidget(reviews));
            }
        });
    }

    private void adjustToolbarTitle(Movie movie) {
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity()))
                .getSupportActionBar()).setTitle(movie.getTitle());
    }
}
