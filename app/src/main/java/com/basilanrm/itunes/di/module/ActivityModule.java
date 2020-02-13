package com.basilanrm.itunes.di.module;

import android.app.Activity;
import android.content.Context;

import com.basilanrm.itunes.di.ActivityContext;
import com.basilanrm.itunes.di.PerActivity;
import com.basilanrm.itunes.ui.activities.main.MainMvpPresenter;
import com.basilanrm.itunes.ui.activities.main.MainPresenter;
import com.basilanrm.itunes.ui.activities.main.MainView;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity providesActivity() {
        return activity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return activity;
    }

    @PerActivity
    @Provides
    MainMvpPresenter<MainView> providesMainPresenter(MainPresenter<MainView> mainMvpPresenter) {
        return mainMvpPresenter;
    }

    @Provides
    CompositeDisposable compositeDisposable() {
        return new CompositeDisposable();
    }
}
