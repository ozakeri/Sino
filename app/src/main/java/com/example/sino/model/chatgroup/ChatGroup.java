
package com.example.sino.model.chatgroup;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.sino.model.db.ChatGroupMember;
import com.example.sino.model.db.ChatMessage;
import com.example.sino.utils.converters.ChatGroupMemberListConverter;
import com.example.sino.utils.converters.ChatMessageListConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity (tableName = "chat_group")
public class ChatGroup {

    @PrimaryKey
    @SerializedName("maxMember")
    @Expose
    public Integer maxMember;
    @SerializedName("notifyAct")
    @Expose
    public Boolean notifyAct;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("chatGroupMembers")
    @Expose
    @TypeConverters({ChatGroupMemberListConverter.class})
    private List<ChatGroupMember> chatGroupMemberList;

    private Long serverGroupId;
    private Boolean privateIs;
    private Integer statusEn;
    @TypeConverters({ChatMessageListConverter.class})
    private List<ChatMessage> chatMessageList;
    private ChatMessage lastChatMessage;
    private Integer countOfUnreadMessage;
    private List<Long> notServerGroupIdList;
    private Integer countOfMembers;

    public Integer getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }

    public Boolean getNotifyAct() {
        return notifyAct;
    }

    public void setNotifyAct(Boolean notifyAct) {
        this.notifyAct = notifyAct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ChatGroupMember> getChatGroupMemberList() {
        return chatGroupMemberList;
    }

    public void setChatGroupMemberList(List<ChatGroupMember> chatGroupMemberList) {
        this.chatGroupMemberList = chatGroupMemberList;
    }

    public Long getServerGroupId() {
        return serverGroupId;
    }

    public void setServerGroupId(Long serverGroupId) {
        this.serverGroupId = serverGroupId;
    }

    public Boolean getPrivateIs() {
        return privateIs;
    }

    public void setPrivateIs(Boolean privateIs) {
        this.privateIs = privateIs;
    }

    public Integer getStatusEn() {
        return statusEn;
    }

    public void setStatusEn(Integer statusEn) {
        this.statusEn = statusEn;
    }

    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    public ChatMessage getLastChatMessage() {
        return lastChatMessage;
    }

    public void setLastChatMessage(ChatMessage lastChatMessage) {
        this.lastChatMessage = lastChatMessage;
    }

    public Integer getCountOfUnreadMessage() {
        return countOfUnreadMessage;
    }

    public void setCountOfUnreadMessage(Integer countOfUnreadMessage) {
        this.countOfUnreadMessage = countOfUnreadMessage;
    }

    public List<Long> getNotServerGroupIdList() {
        return notServerGroupIdList;
    }

    public void setNotServerGroupIdList(List<Long> notServerGroupIdList) {
        this.notServerGroupIdList = notServerGroupIdList;
    }

    public Integer getCountOfMembers() {
        return countOfMembers;
    }

    public void setCountOfMembers(Integer countOfMembers) {
        this.countOfMembers = countOfMembers;
    }
}
