package com.mymovies.di.modules.main.favorites;

import com.mymovies.di.scopes.FragmentScope;
import com.mymovies.fragments.FavoritesMoviesListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FavoritesMoviesFragmentBuilder {

    @ContributesAndroidInjector(modules = {FavoritesMoviesViewModelModule.class})
    @FragmentScope
    public abstract FavoritesMoviesListFragment bindFavoritesMoviesFragment();

}
