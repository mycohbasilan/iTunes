package com.basilanrm.itunes.di.module;

import android.app.Application;
import android.content.Context;

import com.basilanrm.itunes.di.AppContext;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Application providesApplication(){
        return application;
    }

    @Provides
    @AppContext
    Context provideContext(){
        return application;
    }
}
