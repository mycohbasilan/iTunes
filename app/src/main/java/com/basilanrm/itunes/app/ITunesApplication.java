package com.basilanrm.itunes.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.basilanrm.itunes.BuildConfig;
import com.basilanrm.itunes.di.component.AppComponent;
import com.basilanrm.itunes.di.component.DaggerAppComponent;
import com.basilanrm.itunes.di.module.AppModule;

import timber.log.Timber;

public class ITunesApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        setupLogging();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        mAppComponent.inject(this);

    }

    public AppComponent getComponent() {
        return mAppComponent;
    }

    private void setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    /**
     * A tree which logs important information for crash reporting.
     */
    private static class CrashReportingTree extends Timber.Tree {

        @Override
        protected void log(int priority, String tag, @NonNull String message, Throwable t) {
            // exclude verbose and debug logs
            // from being logged to production crashlytics
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            // log exception if it's given
            if (t != null) {
                if (priority == Log.ERROR || priority == Log.WARN) {
                    // log exception for crashlytics
                }
            }
        }
    }
}
