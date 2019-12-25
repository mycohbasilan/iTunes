package com.basilanrm.itunes.ui.base;

import android.content.Context;

import javax.inject.Inject;

public class BasePresenter<V extends BaseMvpView> implements Presenter<V>{

    private V view;
    private Context context;

    @Inject
    public BasePresenter(Context context){
        this.context=context;
    }

    @Override
    public void attachView(V view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        view=null;
    }

    public boolean isViewAttached(){
        return view!=null;
    }

    public V getMVpView(){
        return view;
    }
}