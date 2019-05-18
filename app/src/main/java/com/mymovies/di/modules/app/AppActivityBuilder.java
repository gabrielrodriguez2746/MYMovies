package com.mymovies.di.modules.app;

import com.mymovies.di.modules.main.MainActivityBuilder;

import dagger.Module;

@Module(includes = {MainActivityBuilder.class})
public abstract class AppActivityBuilder {

}
