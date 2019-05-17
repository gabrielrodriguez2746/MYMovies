package com.mymovies.di.modules.app;

import com.mymovies.base.providers.AppResourceProvider;
import com.mymovies.base.providers.ResourceProvider;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;

@Module
public abstract class AppConfigurationModule {

    @Binds
    @Reusable
    public abstract ResourceProvider bindResourceProvider(AppResourceProvider provider);
}
