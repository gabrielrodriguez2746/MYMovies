package com.mymovies.di.modules.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mymovies.data.deserializer.MovieWrapperDeserializer;
import com.mymovies.data.deserializer.ReviewWrapperDeserializer;
import com.mymovies.data.deserializer.TrailerWrapperDeserializer;
import com.mymovies.data.models.MovieWrapper;
import com.mymovies.data.models.ReviewWrapper;
import com.mymovies.data.models.TrailerWrapper;

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
        builder.registerTypeAdapter(ReviewWrapper.class, new ReviewWrapperDeserializer());
        builder.registerTypeAdapter(TrailerWrapper.class, new TrailerWrapperDeserializer());
        return builder.create();
    }
}
