package com.basilanrm.itunes.ui.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basilanrm.itunes.app.ITunesApplication;
import com.basilanrm.itunes.di.component.ActivityComponent;
import com.basilanrm.itunes.di.component.DaggerActivityComponent;
import com.basilanrm.itunes.di.module.ActivityModule;

public class BaseActivity extends AppCompatActivity implements BaseMvpView{

    private ActivityComponent activityComponent;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .appComponent(ITunesApplication.get(this).component())
                    .build();
        }
        return activityComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public boolean isNetworkAvailable() {
        return false;
    }
}