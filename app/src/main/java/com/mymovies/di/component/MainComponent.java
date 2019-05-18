package com.mymovies.di.component;

import com.mymovies.MoviesApplication;
import com.mymovies.di.modules.app.AppActivityBuilder;
import com.mymovies.di.modules.app.AppConfigurationModule;
import com.mymovies.di.modules.app.AppModule;
import com.mymovies.di.modules.app.FactoryModule;
import com.mymovies.di.modules.network.AppNetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        FactoryModule.class,
        AppConfigurationModule.class,
        AppNetworkModule.class,
        AppActivityBuilder.class,
        AppModule.class
})
public interface MainComponent extends AndroidInjector<MoviesApplication> {

    @Component.Builder
    interface Builder {

        MainComponent build();

        @BindsInstance
        Builder application(MoviesApplication application);
    }

}
