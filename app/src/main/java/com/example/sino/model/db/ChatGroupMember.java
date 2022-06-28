package com.example.sino.model.db;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ChatGroupMember implements Parcelable {
    private Long id;
    private Integer privilegeTypeEn;
    private Boolean adminIs;
    private Long appUserId;
    private Long chatGroupId;
    private List<Long> notAppUserIdList;
    private AppUser appUser;

    protected ChatGroupMember(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        if (in.readByte() == 0) {
            privilegeTypeEn = null;
        } else {
            privilegeTypeEn = in.readInt();
        }
        byte tmpAdminIs = in.readByte();
        adminIs = tmpAdminIs == 0 ? null : tmpAdminIs == 1;
        if (in.readByte() == 0) {
            appUserId = null;
        } else {
            appUserId = in.readLong();
        }
        if (in.readByte() == 0) {
            chatGroupId = null;
        } else {
            chatGroupId = in.readLong();
        }
        appUser = in.readParcelable(AppUser.class.getClassLoader());
    }

    public static final Creator<ChatGroupMember> CREATOR = new Creator<ChatGroupMember>() {
        @Override
        public ChatGroupMember createFromParcel(Parcel in) {
            return new ChatGroupMember(in);
        }

        @Override
        public ChatGroupMember[] newArray(int size) {
            return new ChatGroupMember[size];
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
        if (privilegeTypeEn == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(privilegeTypeEn);
        }
        parcel.writeByte((byte) (adminIs == null ? 0 : adminIs ? 1 : 2));
        if (appUserId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(appUserId);
        }
        if (chatGroupId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(chatGroupId);
        }
        parcel.writeParcelable(appUser, i);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrivilegeTypeEn() {
        return privilegeTypeEn;
    }

    public void setPrivilegeTypeEn(Integer privilegeTypeEn) {
        this.privilegeTypeEn = privilegeTypeEn;
    }

    public Boolean getAdminIs() {
        return adminIs;
    }

    public void setAdminIs(Boolean adminIs) {
        this.adminIs = adminIs;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public Long getChatGroupId() {
        return chatGroupId;
    }

    public void setChatGroupId(Long chatGroupId) {
        this.chatGroupId = chatGroupId;
    }

    public List<Long> getNotAppUserIdList() {
        return notAppUserIdList;
    }

    public void setNotAppUserIdList(List<Long> notAppUserIdList) {
        this.notAppUserIdList = notAppUserIdList;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
