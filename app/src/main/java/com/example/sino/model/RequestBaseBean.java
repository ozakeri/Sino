package com.example.sino.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.sino.SinoApplication;
import com.example.sino.utils.common.Util;

public class RequestBaseBean implements Parcelable {

    private Device device = Util.getDevice();
    private String documentUsername = "inspection";
    private String documentPassword = "inspect!gap@1395";
    private String clientId = "2";
    private String version = SinoApplication.getInstance().getVersionName();

    public RequestBaseBean() {
    }

    protected RequestBaseBean(Parcel in) {
        device = in.readParcelable(Device.class.getClassLoader());
        documentUsername = in.readString();
        documentPassword = in.readString();
        clientId = in.readString();
        version = in.readString();
    }

    public static final Creator<RequestBaseBean> CREATOR = new Creator<RequestBaseBean>() {
        @Override
        public RequestBaseBean createFromParcel(Parcel in) {
            return new RequestBaseBean(in);
        }

        @Override
        public RequestBaseBean[] newArray(int size) {
            return new RequestBaseBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(device, i);
        parcel.writeString(documentUsername);
        parcel.writeString(documentPassword);
        parcel.writeString(clientId);
        parcel.writeString(version);
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getDocumentUsername() {
        return documentUsername;
    }

    public void setDocumentUsername(String documentUsername) {
        this.documentUsername = documentUsername;
    }

    public String getDocumentPassword() {
        return documentPassword;
    }

    public void setDocumentPassword(String documentPassword) {
        this.documentPassword = documentPassword;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
