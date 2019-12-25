package com.basilanrm.itunes.di.component;

import com.basilanrm.itunes.ui.activities.main.MainActivity;
import com.basilanrm.itunes.di.PerActivity;
import com.basilanrm.itunes.di.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(modules = {ActivityModule.class}, dependencies = {AppComponent.class})
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}