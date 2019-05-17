package com.mymovies.di.modules.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mymovies.data.deserializer.MovieWrapperDeserializer;
import com.mymovies.data.models.MovieWrapper;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module(includes = {
        AppClientModule.class,
        AppInterceptorsModule.class
})
public class AppNetworkModule {

    @Provides
    @Reusable
    public Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MovieWrapper.class, new MovieWrapperDeserializer());
        return builder.create();
    }
}
