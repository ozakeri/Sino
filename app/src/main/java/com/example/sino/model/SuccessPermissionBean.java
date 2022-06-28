package com.example.sino.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SuccessPermissionBean implements Parcelable {
    private String SUCCESS;
    private ResultBean RESULT;
    private String ERROR;


    protected SuccessPermissionBean(Parcel in) {
        SUCCESS = in.readString();
        RESULT = in.readParcelable(ResultBean.class.getClassLoader());
        ERROR = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(SUCCESS);
        dest.writeParcelable(RESULT, flags);
        dest.writeString(ERROR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SuccessPermissionBean> CREATOR = new Creator<SuccessPermissionBean>() {
        @Override
        public SuccessPermissionBean createFromParcel(Parcel in) {
            return new SuccessPermissionBean(in);
        }

        @Override
        public SuccessPermissionBean[] newArray(int size) {
            return new SuccessPermissionBean[size];
        }
    };

    public String getSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(String SUCCESS) {
        this.SUCCESS = SUCCESS;
    }

    public ResultBean getRESULT() {
        return RESULT;
    }

    public void setRESULT(ResultBean RESULT) {
        this.RESULT = RESULT;
    }

    public String getERROR() {
        return ERROR;
    }

    public void setERROR(String ERROR) {
        this.ERROR = ERROR;
    }
}
