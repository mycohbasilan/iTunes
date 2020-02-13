package com.basilanrm.itunes.ui.base;

public interface BaseSubscriber<T> {
    void onSuccess(T t);

    void onError(String error);
}
