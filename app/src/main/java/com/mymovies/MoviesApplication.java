package com.mymovies;

import android.app.Activity;
import android.app.Application;

import com.mymovies.di.component.DaggerMainComponent;
import com.mymovies.di.component.MainComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MoviesApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        MainComponent component = DaggerMainComponent.builder().application(this).build();
        component.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
