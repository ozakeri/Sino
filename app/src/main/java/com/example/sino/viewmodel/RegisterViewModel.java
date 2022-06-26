package com.example.sino.viewmodel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.sino.R;
import com.example.sino.api.NetworkRepository;
import com.example.sino.model.Event;
import com.example.sino.model.SuccessActivationBean;
import com.example.sino.model.SuccessRegisterBean;
import com.example.sino.model.db.User;
import com.example.sino.utils.Util;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class RegisterViewModel extends ViewModel {

    private static String TAG = RegisterViewModel.class.getSimpleName();
    private NetworkRepository repository;
    MutableLiveData<SuccessRegisterBean> registerMutableLiveData = new MutableLiveData<>();
    MutableLiveData<SuccessActivationBean> activationMutableLiveData = new MutableLiveData<>();
    private User currentUser;

    @ViewModelInject
    public RegisterViewModel(NetworkRepository repository) {
        this.repository = repository;
    }


    public MutableLiveData<SuccessRegisterBean> getRegisterResult() {
        return registerMutableLiveData;
    }

    public MutableLiveData<SuccessActivationBean> getActivationResult() {
        return activationMutableLiveData;
    }

    public void sendPhoneNumber(String INPUT_PARAM, CircularProgressView progressView, View view) {
        System.out.println("INPUT_PARAM=" + INPUT_PARAM);

        Util.showProgress(progressView);
        repository.mobileNoConfirmation(INPUT_PARAM).subscribeOn(Schedulers.io())
                .map(new Function<SuccessRegisterBean, SuccessRegisterBean>() {
                    @Override
                    public SuccessRegisterBean apply(SuccessRegisterBean successRegisterBean) throws Throwable {
                        registerMutableLiveData.postValue(successRegisterBean);
                        Util.hideProgress(progressView);
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> registerMutableLiveData.setValue(result)
                        , throwable -> Log.e(TAG, "sendPhoneNumber: " + throwable.getLocalizedMessage()));
    }

    public void sendActivationCode(String INPUT_PARAM, CircularProgressView progressView) {
        System.out.println("INPUT_PARAM=" + INPUT_PARAM);
        Util.showProgress(progressView);

        repository.activeCodeConfirmation(INPUT_PARAM).subscribeOn(Schedulers.io())
                .map(new Function<SuccessActivationBean, SuccessActivationBean>() {
                    @Override
                    public SuccessActivationBean apply(SuccessActivationBean successActivationBean) throws Throwable {
                        activationMutableLiveData.postValue(successActivationBean);
                        Util.hideProgress(progressView);
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> activationMutableLiveData.setValue(result)
                        , throwable -> Log.e(TAG, "sendActivationCode: " + throwable.getLocalizedMessage()));
    }


    public User getUserByMobileNo(String mobileNo) {
        return repository.getUserByMobileNo(mobileNo);
    }

    public LiveData<List<User>> getAllUser() {
        return repository.getAllUser();
    }

}
