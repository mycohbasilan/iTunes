package com.basilanrm.itunes.ui.base;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter<V extends BaseMvpView> implements Presenter<V> {

    private V view;
    private Context context;
    private CompositeDisposable compositeDisposable;

    @Inject
    public BasePresenter(Context context, CompositeDisposable compositeDisposable) {
        this.context = context;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getMVPView() {
        return view;
    }

    protected <T> void observe(Observable<T> observable, BaseSubscriber<T> baseSubscriber) {
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<T>() {
                    @Override
                    public void onNext(T t) {
                        baseSubscriber.onSuccess(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        baseSubscriber.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (!compositeDisposable.isDisposed()) {
                            compositeDisposable.dispose();
                        }
                    }
                });
        this.compositeDisposable.add(disposable);
    }
}