package com.basilanrm.itunes.ui.base;

public interface BaseMvpView {
    void showError(String error);

    void showLoading();

    void hideLoading();

    boolean isNetworkAvailable();
}
