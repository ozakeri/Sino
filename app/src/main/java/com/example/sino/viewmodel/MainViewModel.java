package com.example.sino.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sino.api.NetworkApi;
import com.example.sino.api.NetworkRepository;
import com.example.sino.db.SinoDao;
import com.example.sino.model.SuccessChatReceiveBean;
import com.example.sino.model.SuccessPermissionBean;
import com.example.sino.model.db.ChatGroup;
import com.example.sino.model.db.User;
import com.example.sino.model.db.UserPermission;
import com.example.sino.model.db.carinfo.SuccessCarInfoBean;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Observable;


@HiltViewModel
public class MainViewModel extends AndroidViewModel {

    @Inject
    NetworkRepository repository;

    @Inject
    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    MutableLiveData<SuccessPermissionBean> userPermissionMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<SuccessPermissionBean> getUserPermissionListResult() {
        return userPermissionMutableLiveData;
    }

    public LiveData<List<User>> getAllUser() {
        return repository.getAllUser();
    }

    public List<ChatGroup> getChatGroupList() {
        return repository.getChatGroupList();
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

    public Observable<SuccessCarInfoBean> getCarInfoVM(String INPUT_PARAM) {
        return repository.getCarInfoRepo(INPUT_PARAM);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
