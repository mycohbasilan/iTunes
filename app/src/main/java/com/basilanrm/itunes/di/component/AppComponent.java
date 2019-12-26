package com.basilanrm.itunes.di.component;

import android.content.Context;

import com.basilanrm.itunes.app.ITunesApplication;
import com.basilanrm.itunes.data.DataManager;
import com.basilanrm.itunes.di.AppContext;
import com.basilanrm.itunes.di.module.AppModule;
import com.basilanrm.itunes.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(ITunesApplication app);

    @AppContext
    Context context();

    DataManager dataManager();
}
