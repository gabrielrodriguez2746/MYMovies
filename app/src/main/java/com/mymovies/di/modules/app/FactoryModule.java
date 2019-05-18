package com.mymovies.di.modules.app;

import androidx.lifecycle.ViewModelProvider;

import com.mymovies.factory.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;

@Module
public abstract class FactoryModule {

    @Binds
    @Reusable
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
