package com.example.sino.api;

import com.example.sino.model.SuccessActivationBean;
import com.example.sino.model.SuccessChatReceiveBean;
import com.example.sino.model.SuccessPermissionBean;
import com.example.sino.model.SuccessRegisterBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApi {

    @GET("/rfServices/mobileNoConfirmation?")
    Observable<SuccessRegisterBean> mobileNoConfirmation(@Query("INPUT_PARAM") String INPUT_PARAM);

    @GET("/rfServices/activationCodeValidation?")
    Observable<SuccessActivationBean> activeCodeConfirmation(@Query("INPUT_PARAM") String INPUT_PARAM);

    @GET("/rfServices/getUserPermissionList?")
    Observable<SuccessPermissionBean> getUserPermissionListApi(@Query("INPUT_PARAM") String INPUT_PARAM);

    @GET("/rfServices/getUserChatMessageList?")
    Observable<SuccessChatReceiveBean> getUserChatMessageListApi(@Query("INPUT_PARAM") String INPUT_PARAM);

    @GET("/rfServices/chatMessageDeliveredReport?")
    Observable<SuccessChatReceiveBean> chatMessageDeliveredReportApi(@Query("INPUT_PARAM") String INPUT_PARAM);
}
