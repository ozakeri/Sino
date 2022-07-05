package com.example.sino.api;


import androidx.lifecycle.LiveData;

import com.example.sino.db.SinoDao;
import com.example.sino.model.SuccessActivationBean;
import com.example.sino.model.SuccessChatReceiveBean;
import com.example.sino.model.SuccessPermissionBean;
import com.example.sino.model.SuccessRegisterBean;
import com.example.sino.model.chatgroup.ChatGroup;
import com.example.sino.model.chatgroup.SuccessChatGroupBean;
import com.example.sino.model.chatgroupmember.SuccessChatGroupMemberBean;
import com.example.sino.model.db.AppUser;
import com.example.sino.model.db.User;
import com.example.sino.model.db.UserPermission;
import com.example.sino.model.carinfo.SuccessCarInfoBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class NetworkRepository {

    private NetworkApi networkApi;
    private SinoDao sinoDao;

    @Inject
    public NetworkRepository(NetworkApi networkApi,SinoDao sinoDao) {
        this.networkApi = networkApi;
        this.sinoDao = sinoDao;
    }

    public Observable<SuccessRegisterBean> mobileNoConfirmation(String INPUT_PARAM) {
        return networkApi.mobileNoConfirmation(INPUT_PARAM + "&IS_ENCRYPED=false");
    }

    public Observable<SuccessActivationBean> activeCodeConfirmation(String INPUT_PARAM) {
        return networkApi.activeCodeConfirmation(INPUT_PARAM + "&IS_ENCRYPED=false");
    }

    public Observable<SuccessPermissionBean> getUserPermissionListRepo(String INPUT_PARAM) {
        return networkApi.getUserPermissionListApi(INPUT_PARAM + "&IS_ENCRYPED=false");
    }

    public Observable<SuccessChatReceiveBean> getUserChatMessageListRepo(String INPUT_PARAM) {
        return networkApi.getUserChatMessageListApi(INPUT_PARAM + "&IS_ENCRYPED=false");
    }

    public Observable<SuccessChatReceiveBean> chatMessageDeliveredReportRepo(String INPUT_PARAM) {
        return networkApi.chatMessageDeliveredReportApi(INPUT_PARAM + "&IS_ENCRYPED=false");
    }

    public Observable<SuccessCarInfoBean> getCarInfoRepo(String INPUT_PARAM) {
        return networkApi.getCarInfoApi(INPUT_PARAM + "&IS_ENCRYPED=false");
    }

    public Observable<SuccessChatGroupBean> getUserChatGroupListRepo(String INPUT_PARAM) {
        return networkApi.getUserChatGroupListApi(INPUT_PARAM + "&IS_ENCRYPED=false");
    }

    public Observable<SuccessChatGroupMemberBean> getUserChatGroupMemberListRepo(String INPUT_PARAM) {
        return networkApi.getUserChatGroupMemberListApi(INPUT_PARAM + "&IS_ENCRYPED=false");
    }

    public Observable<SuccessCarInfoBean> getUserInfoByIdRepo(String INPUT_PARAM) {
        return networkApi.getUserInfoByIdApi(INPUT_PARAM + "&IS_ENCRYPED=false");
    }

    public void insertUser(User user) {
        sinoDao.insertUser(user);
    }

    public void insertPermission(UserPermission permission) {
        sinoDao.insertPermission(permission);
    }

    public void deletePermission(Long userId, String permissionName) {
        sinoDao.deletePermission(userId,permissionName);
    }

    public void deletePermissionByUserId(Long userId) {
        sinoDao.deletePermissionByUserId(userId);
    }

    public User getUserByMobileNo(String mobileNo) {
        return sinoDao.getUserByMobileNo(mobileNo);
    }

    public LiveData<List<User>> getAllUser() {
        return sinoDao.getAllUser();
    }

    public List<UserPermission> getUserPermissionList(Long userId) {
        return sinoDao.getUserPermissionListByUserId(userId);
    }

    public AppUser getAppUserByIdRepo(Long id) {
        return sinoDao.getAppUserById(id);
    }
}
