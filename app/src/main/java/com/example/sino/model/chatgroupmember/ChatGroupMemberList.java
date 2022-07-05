package com.example.sino.model.chatgroupmember;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatGroupMemberList {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("maxMember")
    @Expose
    private int maxMember;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("notifyAct")
    @Expose
    private Boolean notifyAct;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("chatGroupMembers")
    @Expose
    public List<ChatGroupMember> chatGroupMembers = null;

}
