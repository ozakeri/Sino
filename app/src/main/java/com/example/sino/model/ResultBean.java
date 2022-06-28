package com.example.sino.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultBean implements Parcelable {

    @SerializedName("userPermissionList")
    @Expose
    private List<String> userPermissionList;


    protected ResultBean(Parcel in) {
        userPermissionList = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(userPermissionList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
        @Override
        public ResultBean createFromParcel(Parcel in) {
            return new ResultBean(in);
        }

        @Override
        public ResultBean[] newArray(int size) {
            return new ResultBean[size];
        }
    };


    public List<String> getUserPermissionList() {
        return userPermissionList;
    }

    public void setUserPermissionList(List<String> userPermissionList) {
        this.userPermissionList = userPermissionList;
    }
}
