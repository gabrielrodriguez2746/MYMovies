package com.mymovies.di.modules.network;

import com.mymovies.base.interceptors.ApiKeyInterceptor;
import com.mymovies.base.interceptors.HeadersInterceptor;
import com.mymovies.base.interceptors.LogsInterceptor;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;
import dagger.multibindings.IntoSet;
import okhttp3.Interceptor;

@Module
public abstract class AppInterceptorsModule {

    @Binds
    @IntoSet
    @Reusable
    public abstract Interceptor bindHeaderInterceptors(HeadersInterceptor interceptor);

    @Binds
    @IntoSet
    @Reusable
    public abstract Interceptor bindApiKeyInterceptors(ApiKeyInterceptor interceptor);

    @Binds
    @IntoSet
    @Reusable
    public abstract Interceptor bindLoggingInterceptors(LogsInterceptor interceptor);

}
