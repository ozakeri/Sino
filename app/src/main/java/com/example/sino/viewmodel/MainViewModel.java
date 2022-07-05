package com.example.sino.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sino.api.NetworkRepository;
import com.example.sino.model.SuccessChatReceiveBean;
import com.example.sino.model.SuccessPermissionBean;
import com.example.sino.model.chatgroup.ChatGroup;
import com.example.sino.model.chatgroup.SuccessChatGroupBean;
import com.example.sino.model.db.User;
import com.example.sino.model.db.UserPermission;
import com.example.sino.model.carinfo.SuccessCarInfoBean;
import com.example.sino.utils.common.Util;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


@HiltViewModel
public class MainViewModel extends AndroidViewModel {

    @Inject
    NetworkRepository repository;

    @Inject
    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    MutableLiveData<SuccessPermissionBean> userPermissionMutableLiveData = new MutableLiveData<>();
    MutableLiveData<SuccessCarInfoBean> carInfoMutableLiveData = new MutableLiveData<>();
    MutableLiveData<SuccessChatGroupBean> userChatGroupListMutableLiveData = new MutableLiveData<>();
    MutableLiveData<SuccessCarInfoBean> chatGroupMemberListMutableLiveData = new MutableLiveData<>();
    MutableLiveData<SuccessCarInfoBean> getUserInfoByIdMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<SuccessPermissionBean> getUserPermissionListResult() {
        return userPermissionMutableLiveData;
    }

    public LiveData<List<User>> getAllUser() {
        return repository.getAllUser();
    }

    public List<UserPermission> getUserPermission(Long userId) {
        return repository.getUserPermissionList(userId);
    }

    public void insertUser(User user) {
        repository.insertUser(user);
    }

    public void insertPermission(UserPermission permission) {
        repository.insertPermission(permission);
    }

    public void deleteUserPermission(Long userId, String permissionName) {
        repository.deletePermission(userId, permissionName);
    }

    public void deletePermissionByUserId(Long userId) {
        repository.deletePermissionByUserId(userId);
    }

    public Observable<SuccessPermissionBean> getUserPermissionListVM(String INPUT_PARAM) {
        return repository.getUserPermissionListRepo(INPUT_PARAM);
    }

    public Observable<SuccessChatReceiveBean> getUserChatMessageListVM(String INPUT_PARAM) {
        return repository.getUserChatMessageListRepo(INPUT_PARAM);
    }

    public Observable<SuccessChatReceiveBean> chatMessageDeliveredReportVM(String INPUT_PARAM) {
        return repository.chatMessageDeliveredReportRepo(INPUT_PARAM);
    }

    public MutableLiveData<SuccessCarInfoBean> getCarInfoResult() {
        return carInfoMutableLiveData;
    }

    public MutableLiveData<SuccessChatGroupBean> getUserChatGroupList() {
        return userChatGroupListMutableLiveData;
    }

    public MutableLiveData<SuccessCarInfoBean> getChatGroupMemberList() {
        return chatGroupMemberListMutableLiveData;
    }

    public MutableLiveData<SuccessCarInfoBean> getUserInfoById() {
        return getUserInfoByIdMutableLiveData;
    }

    public void getCarInfo(String INPUT_PARAM, CircularProgressView progressView) {
        System.out.println("INPUT_PARAM=" + INPUT_PARAM);
        Util.showProgress(progressView);

        repository.getCarInfoRepo(INPUT_PARAM).subscribeOn(Schedulers.io())
                .map(new Function<SuccessCarInfoBean, SuccessCarInfoBean>() {
                    @Override
                    public SuccessCarInfoBean apply(SuccessCarInfoBean successCarInfoBean) throws Throwable {
                        carInfoMutableLiveData.postValue(successCarInfoBean);
                        Util.hideProgress(progressView);
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> carInfoMutableLiveData.setValue(result)
                        , throwable -> Log.e("TAG", "getCarInfo: " + throwable.getLocalizedMessage()));
    }

    public void getUserChatGroupList(String INPUT_PARAM, CircularProgressView progressView) {
        System.out.println("INPUT_PARAM=" + INPUT_PARAM);
        Util.showProgress(progressView);

        repository.getUserChatGroupListRepo(INPUT_PARAM).subscribeOn(Schedulers.io())
                .map(new Function<SuccessChatGroupBean, SuccessChatGroupBean>() {
                    @Override
                    public SuccessChatGroupBean apply(SuccessChatGroupBean successChatGroupBean) throws Throwable {
                        userChatGroupListMutableLiveData.postValue(successChatGroupBean);
                        Util.hideProgress(progressView);
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> userChatGroupListMutableLiveData.setValue(result)
                        , throwable -> Log.e("TAG", "getUserChatGroupList: " + throwable.getLocalizedMessage()));
    }

    public void getUserChatGroupMemberList(String INPUT_PARAM) {
        System.out.println("INPUT_PARAM=" + INPUT_PARAM);
        //Util.showProgress(progressView);

        repository.getUserChatGroupMemberListRepo(INPUT_PARAM).subscribeOn(Schedulers.io())
                .map(new Function<SuccessCarInfoBean, SuccessCarInfoBean>() {
                    @Override
                    public SuccessCarInfoBean apply(SuccessCarInfoBean successCarInfoBean) throws Throwable {
                        chatGroupMemberListMutableLiveData.postValue(successCarInfoBean);
                       // Util.hideProgress(progressView);
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> chatGroupMemberListMutableLiveData.setValue(result)
                        , throwable -> Log.e("TAG", "getUserChatGroupMemberList: " + throwable.getLocalizedMessage()));
    }

    public void getUserInfoById(String INPUT_PARAM) {
        System.out.println("INPUT_PARAM=" + INPUT_PARAM);
        //Util.showProgress(progressView);

        repository.getUserInfoByIdRepo(INPUT_PARAM).subscribeOn(Schedulers.io())
                .map(new Function<SuccessCarInfoBean, SuccessCarInfoBean>() {
                    @Override
                    public SuccessCarInfoBean apply(SuccessCarInfoBean successCarInfoBean) throws Throwable {
                        getUserInfoByIdMutableLiveData.postValue(successCarInfoBean);
                       // Util.hideProgress(progressView);
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getUserInfoByIdMutableLiveData.setValue(result)
                        , throwable -> Log.e("TAG", "getUserInfoById: " + throwable.getLocalizedMessage()));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
