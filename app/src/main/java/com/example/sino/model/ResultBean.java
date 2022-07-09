package com.example.sino.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.sino.model.db.ChatMessage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultBean{

    @SerializedName("userPermissionList")
    @Expose
    private List<String> userPermissionList;

    @SerializedName("chatMessageReceiverList")
    @Expose
    public List<ChatMessageReceiver> chatMessageReceiverList = null;

    public List<String> getUserPermissionList() {
        return userPermissionList;
    }

    public void setUserPermissionList(List<String> userPermissionList) {
        this.userPermissionList = userPermissionList;
    }

    public List<ChatMessageReceiver> getChatMessageReceiverList() {
        return chatMessageReceiverList;
    }

    public void setChatMessageReceiverList(List<ChatMessageReceiver> chatMessageReceiverList) {
        this.chatMessageReceiverList = chatMessageReceiverList;
    }
}
