package com.basilanrm.itunes.ui.base;

public interface Presenter<V> {
    void attachView(V view);

    void detachView();
}
