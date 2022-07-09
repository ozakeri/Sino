package com.example.sino.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.sino.SinoApplication;
import com.example.sino.model.db.ChatMessage;
import com.example.sino.model.db.TmpChatMessage;
import com.example.sino.utils.common.Util;

public class RequestBaseBean implements Parcelable {

    private Device device = Util.getDevice();
    private String documentUsername = "inspection";
    private String documentPassword = "inspect!gap@1395";
    private String clientId = "2";
    private String version = SinoApplication.getInstance().getVersionName();
    private TmpChatMessage chatMessage;

    public RequestBaseBean() {
    }


    protected RequestBaseBean(Parcel in) {
        device = in.readParcelable(Device.class.getClassLoader());
        documentUsername = in.readString();
        documentPassword = in.readString();
        clientId = in.readString();
        version = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(device, flags);
        dest.writeString(documentUsername);
        dest.writeString(documentPassword);
        dest.writeString(clientId);
        dest.writeString(version);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public TmpChatMessage getTmpChatMessage() {
        return chatMessage;
    }

    public void setTmpChatMessage(TmpChatMessage tmpChatMessage) {
        this.chatMessage = tmpChatMessage;
    }
}
