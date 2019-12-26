package com.basilanrm.itunes.di.module;

import android.app.Application;
import android.content.Context;

import com.basilanrm.itunes.constants.Constants;
import com.basilanrm.itunes.di.AppContext;
import com.basilanrm.itunes.di.PrefsInfo;

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

    @Provides
    @PrefsInfo
    String providePreferenceName() {
        return Constants.PREF_NAME;
    }
}
