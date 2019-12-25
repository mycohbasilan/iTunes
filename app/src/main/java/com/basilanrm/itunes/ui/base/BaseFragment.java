package com.basilanrm.itunes.ui.base;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.basilanrm.itunes.di.component.ActivityComponent;

public class BaseFragment extends Fragment implements BaseMvpView {

    private static final String TAG = BaseFragment.class.getSimpleName();
    private OnFragmentInteractionListener listener;

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            Log.d(TAG, "Parent Activity not implement FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        void showErrorCallBack(String error);

        ActivityComponent getActivityComponentCallBack();
    }

    public ActivityComponent getActivityComponent(){
        return listener.getActivityComponentCallBack();
    }
}
