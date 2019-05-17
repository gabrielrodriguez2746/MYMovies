package com.mymovies.fragments;

import android.content.Context;
import android.os.Bundle;
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
import com.mymovies.models.RecyclerViewConfiguration;
import com.mymovies.viewmodels.PopularMoviesViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class PopularMoviesListFragment extends Fragment {

    private FragmentMoviesListBinding binding;
    private MYMoviesAdapter adapter;
    private PopularMoviesViewModel viewModel;

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
            adapter = new MYMoviesAdapter();
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_list, container, false);
            binding.setRecyclerConfiguration(new RecyclerViewConfiguration(
                    new GridLayoutManager(getContext(), 2), adapter));
        }
        return binding != null ? binding.getRoot() : super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(this, factory).get(PopularMoviesViewModel.class);
        }
        viewModel.getItemsLiveData().observe(getViewLifecycleOwner(), movies -> adapter.submitList(movies));
    }
}
