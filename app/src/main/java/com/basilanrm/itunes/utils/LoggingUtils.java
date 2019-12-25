package com.basilanrm.itunes.utils;

import com.basilanrm.itunes.BuildConfig;

import timber.log.Timber;

public class LoggingUtils {

    public static void logError(String message, String tag) {
        if (BuildConfig.DEBUG) {
            Timber.e(message);
        }
    }

    /**
     * Logs exception
     *
     * @param throwable the {@link Throwable} to be logged
     */
    public static void logException(Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Timber.e(throwable);
        }
    }
}
