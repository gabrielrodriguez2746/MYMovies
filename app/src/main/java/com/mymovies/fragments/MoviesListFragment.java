package com.mymovies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mymovies.R;
import com.mymovies.adapters.MYMoviesAdapter;
import com.mymovies.data.models.Movie;
import com.mymovies.databinding.FragmentMoviesListBinding;
import com.mymovies.decorators.MediaSpaceDecorator;
import com.mymovies.listeners.OnFragmentInteraction;
import com.mymovies.models.RecyclerViewConfiguration;
import com.mymovies.viewmodels.main.FavoritesMoviesViewModel;
import com.mymovies.viewmodels.main.MoviesListViewModel;
import com.mymovies.viewmodels.main.PopularMoviesViewModel;
import com.mymovies.viewmodels.main.TopRatedMoviesViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MoviesListFragment extends MoviesListRestoreStateFragment implements MYMoviesAdapter.OnMovieClicked {

    private FragmentMoviesListBinding binding;
    private MYMoviesAdapter adapter;
    private MoviesListViewModel viewModel;

    @Inject
    ViewModelProvider.Factory factory;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            DisplayMetrics displayMetrics = getDisplayMetrics();
            adapter = new MYMoviesAdapter(displayMetrics, this);
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
            viewModel = ViewModelProviders.of(this, factory).get(PopularMoviesViewModel.class);
        }
        viewModel.getItemsLiveData().observe(getViewLifecycleOwner(), this::processItems);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        viewModel.getItemsLiveData().removeObservers(getViewLifecycleOwner());
        if (item.getItemId() == R.id.destination_top_rated) {
            Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity()))
                    .getSupportActionBar()).setTitle(getString(R.string.app_top_rated));
            viewModel = ViewModelProviders.of(this, factory).get(TopRatedMoviesViewModel.class);
        } else {
            Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity()))
                    .getSupportActionBar()).setTitle(getString(R.string.app_popular));
            viewModel = ViewModelProviders.of(this, factory).get(PopularMoviesViewModel.class);
        }
        viewModel.getItemsLiveData().observe(getViewLifecycleOwner(), this::processItems);
        return super.onOptionsItemSelected(item);
    }

    private void processItems(PagedList<Movie> movies) {
        boolean adapterWasEmpty = adapter.getItemCount() == 0;
        adapter.submitList(movies);
        if (adapterWasEmpty && lastVisibleIndex != -1) {
            layoutManager.scrollToPosition(lastVisibleIndex);
        }
    }

    @Override
    public void onMovieClicked(int movieId) {
        ((OnFragmentInteraction) Objects.requireNonNull(getActivity()))
                .onItemClicked(viewModel.getClass().getSimpleName(), String.valueOf(movieId));
    }

    @NotNull
    private DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
