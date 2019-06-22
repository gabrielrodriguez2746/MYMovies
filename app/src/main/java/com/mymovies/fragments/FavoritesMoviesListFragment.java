package com.mymovies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mymovies.R;
import com.mymovies.adapters.FavoritesMoviesAdapter;
import com.mymovies.adapters.MYMoviesAdapter;
import com.mymovies.data.models.Movie;
import com.mymovies.databinding.FragmentMoviesListBinding;
import com.mymovies.decorators.MediaSpaceDecorator;
import com.mymovies.listeners.OnFragmentInteraction;
import com.mymovies.models.RecyclerViewConfiguration;
import com.mymovies.viewmodels.FavoritesMoviesViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class FavoritesMoviesListFragment extends MoviesListRestoreStateFragment implements MYMoviesAdapter.OnMovieClicked {

    public static String FAVORITES_FRAGMENT = "FAVORITES_FRAGMENT";

    private FragmentMoviesListBinding binding;
    private FavoritesMoviesAdapter adapter;
    private FavoritesMoviesViewModel viewModel;

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
            DisplayMetrics displayMetrics = getDisplayMetrics();
            adapter = new FavoritesMoviesAdapter(displayMetrics, this);
            int rowsNumber = Objects.requireNonNull(getContext()).getResources().getInteger(R.integer.app_adapter_rows);
            layoutManager = new GridLayoutManager(getContext(), rowsNumber);
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_list, container, false);
            binding.setRecyclerConfiguration(new RecyclerViewConfiguration(
                    layoutManager, adapter,
                    new MediaSpaceDecorator((getResources().getDimensionPixelSize(R.dimen.space_small)))));
        }
        return binding != null ? binding.getRoot() : super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(this, factory).get(FavoritesMoviesViewModel.class);
        }
        viewModel.getFavorites();
        viewModel.getItemsLiveData().observe(getViewLifecycleOwner(), this::processItems);
    }

    private void processItems(List<Movie> movies) {
        boolean adapterWasEmpty = adapter.getItemCount() == 0;
        adapter.submitList(movies);
        if (adapterWasEmpty && lastVisibleIndex != -1) {
            layoutManager.scrollToPosition(lastVisibleIndex);
        }
    }

    @NotNull
    private DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    @Override
    public void onMovieClicked(int movieId) {
        ((OnFragmentInteraction) Objects.requireNonNull(getActivity()))
                .onItemClicked(FAVORITES_FRAGMENT, String.valueOf(movieId));
    }
}