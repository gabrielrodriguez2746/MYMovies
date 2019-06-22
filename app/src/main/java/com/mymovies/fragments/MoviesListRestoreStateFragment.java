package com.mymovies.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

public abstract class MoviesListRestoreStateFragment extends Fragment {

    private static final String LAYOUT_MANAGER_INDEX = "LAYOUT_MANAGER_INDEX";

    protected GridLayoutManager layoutManager;

    protected int lastVisibleIndex = -1;

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        lastVisibleIndex = getLastVisibleIndexFromSaveInstanceState(savedInstanceState);
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(LAYOUT_MANAGER_INDEX, getLastVisiblePosition());
        super.onSaveInstanceState(outState);
    }

    private int getLastVisiblePosition() {
        return layoutManager != null ? layoutManager.findFirstVisibleItemPosition() : -1;
    }

    private int getLastVisibleIndexFromSaveInstanceState(@Nullable Bundle state) {
        return state != null ? state.getInt(LAYOUT_MANAGER_INDEX, -1) : -1;
    }
}
