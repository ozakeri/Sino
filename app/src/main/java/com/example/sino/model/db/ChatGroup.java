package com.example.sino.model.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.TypeConverters;

import com.example.sino.utils.converters.ChatGroupMemberListConverter;
import com.example.sino.utils.converters.ChatMessageListConverter;

import java.util.List;

public class ChatGroup implements Parcelable {

    private Long id;
    private Long serverGroupId;
    private String name;
    private Integer maxMember;
    private Boolean notifyAct;
    private Integer statusEn;

    @TypeConverters({ChatGroupMemberListConverter.class})
    private List<ChatGroupMember> chatGroupMemberList;
    @TypeConverters({ChatMessageListConverter.class})
    private List<ChatMessage> chatMessageList;
    private transient ChatMessage lastChatMessage;
    private transient Integer countOfUnreadMessage;
    private transient List<Long> notServerGroupIdList;
    private transient Integer countOfMembers;

    public ChatGroup() {
    }

    protected ChatGroup(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        if (in.readByte() == 0) {
            serverGroupId = null;
        } else {
            serverGroupId = in.readLong();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            maxMember = null;
        } else {
            maxMember = in.readInt();
        }
        byte tmpNotifyAct = in.readByte();
        notifyAct = tmpNotifyAct == 0 ? null : tmpNotifyAct == 1;
        if (in.readByte() == 0) {
            statusEn = null;
        } else {
            statusEn = in.readInt();
        }
    }

    public static final Creator<ChatGroup> CREATOR = new Creator<ChatGroup>() {
        @Override
        public ChatGroup createFromParcel(Parcel in) {
            return new ChatGroup(in);
        }

        @Override
        public ChatGroup[] newArray(int size) {
            return new ChatGroup[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        if (serverGroupId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(serverGroupId);
        }
        parcel.writeString(name);
        if (maxMember == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(maxMember);
        }
        parcel.writeByte((byte) (notifyAct == null ? 0 : notifyAct ? 1 : 2));
        if (statusEn == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(statusEn);
        }
        parcel.writeTypedList(chatGroupMemberList);
        parcel.writeTypedList(chatMessageList);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServerGroupId() {
        return serverGroupId;
    }

    public void setServerGroupId(Long serverGroupId) {
        this.serverGroupId = serverGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Integer getStatusEn() {
        return statusEn;
    }

    public void setStatusEn(Integer statusEn) {
        this.statusEn = statusEn;
    }

    public List<ChatGroupMember> getChatGroupMemberList() {
        return chatGroupMemberList;
    }

    public void setChatGroupMemberList(List<ChatGroupMember> chatGroupMemberList) {
        this.chatGroupMemberList = chatGroupMemberList;
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
