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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mymovies.R;
import com.mymovies.adapters.MYMoviesAdapter;
import com.mymovies.databinding.FragmentMoviesListBinding;
import com.mymovies.decorators.MediaSpaceDecorator;
import com.mymovies.listeners.OnFragmentInteraction;
import com.mymovies.models.RecyclerViewConfiguration;
import com.mymovies.viewmodels.TopRatedMoviesViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class TopRatedMoviesListFragment extends Fragment implements MYMoviesAdapter.OnMovieClicked {

    public static String TOP_RATED_FRAGMENT = "TOP_RATED_FRAGMENT";

    private FragmentMoviesListBinding binding;
    private MYMoviesAdapter adapter;
    private TopRatedMoviesViewModel viewModel;

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
            adapter = new MYMoviesAdapter(displayMetrics, this);
            int rowsNumber = Objects.requireNonNull(getContext()).getResources().getInteger(R.integer.app_adapter_rows);
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_list, container, false);
            binding.setRecyclerConfiguration(new RecyclerViewConfiguration(
                    new GridLayoutManager(getContext(), rowsNumber), adapter,
                    new MediaSpaceDecorator((getResources().getDimensionPixelSize(R.dimen.space_small)))));
        }
        return binding != null ? binding.getRoot() : super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(this, factory).get(TopRatedMoviesViewModel.class);
        }
        viewModel.getItemsLiveData().observe(getViewLifecycleOwner(), movies -> adapter.submitList(movies));
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
                .onItemClicked(TOP_RATED_FRAGMENT, String.valueOf(movieId));
    }
}
