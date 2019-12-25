package com.basilanrm.itunes.di.module;

import com.basilanrm.itunes.BuildConfig;
import com.basilanrm.itunes.constants.Constants;
import com.basilanrm.itunes.data.ApiInterface;
import com.basilanrm.itunes.utils.OkHttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String BASE_URL = "NAME_BASE_URL";

    @Provides
    @Named(BASE_URL)
    String provideBaseUrlString() {
        return BuildConfig.BASE_URL;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(RxJava2CallAdapterFactory factory,
                             @Named(BASE_URL) String base_url,
                             Converter.Factory converter, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(base_url)
                .addCallAdapterFactory(factory)
                .addConverterFactory(converter)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            client.addInterceptor(new OkHttpLoggingInterceptor());
        }

        return client.build();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    ApiInterface provideApiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }
}
