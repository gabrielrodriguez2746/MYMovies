package com.mymovies.activities;

import android.os.Bundle;
import android.view.animation.AnticipateOvershootInterpolator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;

import com.mymovies.R;
import com.mymovies.databinding.ActivityMainBinding;
import com.mymovies.fragments.DetailMovieFragment;
import com.mymovies.listeners.OnFragmentInteraction;
import com.mymovies.viewmodels.main.FavoritesMoviesViewModel;
import com.mymovies.viewmodels.main.PopularMoviesViewModel;
import com.mymovies.viewmodels.main.TopRatedMoviesViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector, OnFragmentInteraction {

    private static final long ANIMATION_DURATION = 400L;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private ActivityMainBinding binding;
    private NavController navController;

    private ChangeBounds changeBoundsTransition;

    private ConstraintSet detailConstraintSet;
    private ConstraintSet homeConstraintSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        initBoundTransition();
        initConstrains();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.fgNavController);
        NavigationUI.setupWithNavController(binding.bnHome, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    private void initConstrains() {
        detailConstraintSet = new ConstraintSet();
        detailConstraintSet.clone(this, R.layout.activity_main_detail_constains);
        homeConstraintSet = new ConstraintSet();
        homeConstraintSet.clone(this, R.layout.activity_main_home_constrains);
    }

    private void initBoundTransition() {
        changeBoundsTransition = new ChangeBounds();
        changeBoundsTransition.setDuration(ANIMATION_DURATION);
        changeBoundsTransition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
    }

    @Override
    public boolean onSupportNavigateUp() {
        boolean navigateUp = navController.navigateUp();
        if (navigateUp) {
            createActivityTransition(homeConstraintSet);
        }
        return navigateUp;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onItemClicked(String fragment, String id) {
        Bundle data = new Bundle();
        data.putInt(DetailMovieFragment.MOVIE_ID_KEY, Integer.valueOf(id));
        if (fragment.equals(PopularMoviesViewModel.class.getSimpleName())) {
            data.putInt(DetailMovieFragment.MOVIE_DETAIL_TYPE, 0);
        } else if (fragment.equals(TopRatedMoviesViewModel.class.getSimpleName())) {
            data.putInt(DetailMovieFragment.MOVIE_DETAIL_TYPE, 1);
        } else if (fragment.equals(FavoritesMoviesViewModel.class.getSimpleName())) {
            data.putInt(DetailMovieFragment.MOVIE_DETAIL_TYPE, 2);
        }
        createActivityTransition(detailConstraintSet);
        navController.navigate(R.id.action_detail, data);
    }

    private void createActivityTransition(ConstraintSet constraintSet) {
        TransitionManager.beginDelayedTransition(binding.clParent, changeBoundsTransition);
        constraintSet.applyTo(binding.clParent);
    }
}
