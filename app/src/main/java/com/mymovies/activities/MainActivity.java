package com.mymovies.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.mymovies.R;
import com.mymovies.fragments.DetailMovieFragment;
import com.mymovies.fragments.PopularMoviesListFragment;
import com.mymovies.listeners.OnFragmentInteraction;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector, OnFragmentInteraction {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private NavController navController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.fgNavController);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onItemClicked(String fragment, String id) {
        Bundle data = new Bundle();
        if (fragment.equals(PopularMoviesListFragment.POPULAR_FRAGMENT)) {
            data.putInt(DetailMovieFragment.MOVIE_ID_KEY, Integer.valueOf(id));
            data.putInt(DetailMovieFragment.MOVIE_DETAIL_TYPE, 0);
            navController.navigate(R.id.action_detail, data);
        }
    }
}
