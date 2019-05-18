package com.mymovies.base.interceptors;

import com.mymovies.R;
import com.mymovies.base.providers.ResourceProvider;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {

    private ResourceProvider provider;

    @Inject
    public ApiKeyInterceptor(ResourceProvider provider) {
        this.provider = provider;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl.Builder urlRequest = request.url().newBuilder()
                .addQueryParameter("api_key", provider.getString(R.string.movies_api_key));
        Request.Builder builder = request.newBuilder();
        builder.url(urlRequest.build());
        return chain.proceed(builder.build());
    }
}
