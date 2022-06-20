package com.example.sino.api;


import androidx.lifecycle.LiveData;

import com.example.sino.db.SinoDao;
import com.example.sino.model.SuccessActivationBean;
import com.example.sino.model.SuccessRegisterBean;
import com.example.sino.model.db.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class NetworkRepository {

    private NetworkApi networkApi;
    private SinoDao sinoDao;

    @Inject
    public NetworkRepository(NetworkApi networkApi, SinoDao sinoDao) {
        this.networkApi = networkApi;
        this.sinoDao = sinoDao;
    }

    public Observable<SuccessRegisterBean> mobileNoConfirmation(String INPUT_PARAM) {
        return networkApi.mobileNoConfirmation(INPUT_PARAM + "&IS_ENCRYPED=false");
    }

    public Observable<SuccessActivationBean> activeCodeConfirmation(String INPUT_PARAM) {
        return networkApi.activeCodeConfirmation(INPUT_PARAM + "&IS_ENCRYPED=false");
    }

    public void insertUser(User user) {
        sinoDao.insertUser(user);
    }

    public User getUserByMobileNo(String mobileNo) {
        return sinoDao.getUserByMobileNo(mobileNo);
    }

    public LiveData<List<User>> getAllUser() {
        return sinoDao.getAllUser();
    }
}
