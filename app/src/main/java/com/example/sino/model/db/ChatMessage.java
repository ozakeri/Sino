package com.example.sino.model.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ChatMessage implements Parcelable {


    @SerializedName("chatGroupId")
    @Expose
    public Integer chatGroupId;
    @SerializedName("dateCreation")
    @Expose
    public String dateCreation;
    @SerializedName("attachFileId")
    @Expose
    public Integer attachFileId;
    @SerializedName("senderUserId")
    @Expose
    public Integer senderUserId;
    @SerializedName("sendDate")
    @Expose
    public String sendDate;
    @SerializedName("attachFileSize")
    @Expose
    public Integer attachFileSize;
    @SerializedName("attachFileUserFileName")
    @Expose
    public String attachFileUserFileName;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("status_text")
    @Expose
    public String statusText;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("clientMessageId")
    @Expose
    public Long clientMessageId;
    @SerializedName("userCreationId")
    @Expose
    public Integer userCreationId;

    private Long serverMessageId;
    private java.util.Date validUntilDate;
    private String message;
    private String attachFileLocalPath;
    private String attachFileRemoteUrl;
    private Boolean deliverIs;
    private java.util.Date deliverDate;
    private Boolean readIs;
    private java.util.Date readDate;
    private Integer sendingStatusEn;
    private java.util.Date sendingStatusDate;
    private Integer attachFileSentSize;
    private Integer attachFileReceivedSize;
    private Long senderAppUserId;
    private Long receiverAppUserId;
    private AppUser senderAppUser;
    private boolean localAttachFileExist;
    private java.util.Date readDateFrom;
    private Long senderAppUserIdNot;

    public ChatMessage() {
    }

    protected ChatMessage(Parcel in) {
        if (in.readByte() == 0) {
            chatGroupId = null;
        } else {
            chatGroupId = in.readInt();
        }
        dateCreation = in.readString();
        if (in.readByte() == 0) {
            attachFileId = null;
        } else {
            attachFileId = in.readInt();
        }
        if (in.readByte() == 0) {
            senderUserId = null;
        } else {
            senderUserId = in.readInt();
        }
        sendDate = in.readString();
        if (in.readByte() == 0) {
            attachFileSize = null;
        } else {
            attachFileSize = in.readInt();
        }
        attachFileUserFileName = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        statusText = in.readString();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        if (in.readByte() == 0) {
            clientMessageId = null;
        } else {
            clientMessageId = in.readLong();
        }
        if (in.readByte() == 0) {
            userCreationId = null;
        } else {
            userCreationId = in.readInt();
        }
        if (in.readByte() == 0) {
            serverMessageId = null;
        } else {
            serverMessageId = in.readLong();
        }
        message = in.readString();
        attachFileLocalPath = in.readString();
        attachFileRemoteUrl = in.readString();
        byte tmpDeliverIs = in.readByte();
        deliverIs = tmpDeliverIs == 0 ? null : tmpDeliverIs == 1;
        byte tmpReadIs = in.readByte();
        readIs = tmpReadIs == 0 ? null : tmpReadIs == 1;
        if (in.readByte() == 0) {
            sendingStatusEn = null;
        } else {
            sendingStatusEn = in.readInt();
        }
        if (in.readByte() == 0) {
            attachFileSentSize = null;
        } else {
            attachFileSentSize = in.readInt();
        }
        if (in.readByte() == 0) {
            attachFileReceivedSize = null;
        } else {
            attachFileReceivedSize = in.readInt();
        }
        if (in.readByte() == 0) {
            senderAppUserId = null;
        } else {
            senderAppUserId = in.readLong();
        }
        if (in.readByte() == 0) {
            receiverAppUserId = null;
        } else {
            receiverAppUserId = in.readLong();
        }
        senderAppUser = in.readParcelable(AppUser.class.getClassLoader());
        localAttachFileExist = in.readByte() != 0;
        if (in.readByte() == 0) {
            senderAppUserIdNot = null;
        } else {
            senderAppUserIdNot = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (chatGroupId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(chatGroupId);
        }
        dest.writeString(dateCreation);
        if (attachFileId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(attachFileId);
        }
        if (senderUserId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(senderUserId);
        }
        dest.writeString(sendDate);
        if (attachFileSize == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(attachFileSize);
        }
        dest.writeString(attachFileUserFileName);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(statusText);
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
        if (clientMessageId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(clientMessageId);
        }
        if (userCreationId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userCreationId);
        }
        if (serverMessageId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(serverMessageId);
        }
        dest.writeString(message);
        dest.writeString(attachFileLocalPath);
        dest.writeString(attachFileRemoteUrl);
        dest.writeByte((byte) (deliverIs == null ? 0 : deliverIs ? 1 : 2));
        dest.writeByte((byte) (readIs == null ? 0 : readIs ? 1 : 2));
        if (sendingStatusEn == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sendingStatusEn);
        }
        if (attachFileSentSize == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(attachFileSentSize);
        }
        if (attachFileReceivedSize == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(attachFileReceivedSize);
        }
        if (senderAppUserId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(senderAppUserId);
        }
        if (receiverAppUserId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(receiverAppUserId);
        }
        dest.writeParcelable(senderAppUser, flags);
        dest.writeByte((byte) (localAttachFileExist ? 1 : 0));
        if (senderAppUserIdNot == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(senderAppUserIdNot);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ChatMessage> CREATOR = new Creator<ChatMessage>() {
        @Override
        public ChatMessage createFromParcel(Parcel in) {
            return new ChatMessage(in);
        }

        @Override
        public ChatMessage[] newArray(int size) {
            return new ChatMessage[size];
        }
    };

    public Integer getChatGroupId() {
        return chatGroupId;
    }

    public void setChatGroupId(Integer chatGroupId) {
        this.chatGroupId = chatGroupId;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Integer getAttachFileId() {
        return attachFileId;
    }

    public void setAttachFileId(Integer attachFileId) {
        this.attachFileId = attachFileId;
    }

    public Integer getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Integer senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public Integer getAttachFileSize() {
        return attachFileSize;
    }

    public void setAttachFileSize(Integer attachFileSize) {
        this.attachFileSize = attachFileSize;
    }

    public String getAttachFileUserFileName() {
        return attachFileUserFileName;
    }

    public void setAttachFileUserFileName(String attachFileUserFileName) {
        this.attachFileUserFileName = attachFileUserFileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getClientMessageId() {
        return clientMessageId;
    }

    public void setClientMessageId(Long clientMessageId) {
        this.clientMessageId = clientMessageId;
    }

    public Integer getUserCreationId() {
        return userCreationId;
    }

    public void setUserCreationId(Integer userCreationId) {
        this.userCreationId = userCreationId;
    }

    public Long getServerMessageId() {
        return serverMessageId;
    }

    public void setServerMessageId(Long serverMessageId) {
        this.serverMessageId = serverMessageId;
    }

    public Date getValidUntilDate() {
        return validUntilDate;
    }

    public void setValidUntilDate(Date validUntilDate) {
        this.validUntilDate = validUntilDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAttachFileLocalPath() {
        return attachFileLocalPath;
    }

    public void setAttachFileLocalPath(String attachFileLocalPath) {
        this.attachFileLocalPath = attachFileLocalPath;
    }

    public String getAttachFileRemoteUrl() {
        return attachFileRemoteUrl;
    }

    public void setAttachFileRemoteUrl(String attachFileRemoteUrl) {
        this.attachFileRemoteUrl = attachFileRemoteUrl;
    }

    public Boolean getDeliverIs() {
        return deliverIs;
    }

    public void setDeliverIs(Boolean deliverIs) {
        this.deliverIs = deliverIs;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public Boolean getReadIs() {
        return readIs;
    }

    public void setReadIs(Boolean readIs) {
        this.readIs = readIs;
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }

    public Integer getSendingStatusEn() {
        return sendingStatusEn;
    }

    public void setSendingStatusEn(Integer sendingStatusEn) {
        this.sendingStatusEn = sendingStatusEn;
    }

    public Date getSendingStatusDate() {
        return sendingStatusDate;
    }

    public void setSendingStatusDate(Date sendingStatusDate) {
        this.sendingStatusDate = sendingStatusDate;
    }

    public Integer getAttachFileSentSize() {
        return attachFileSentSize;
    }

    public void setAttachFileSentSize(Integer attachFileSentSize) {
        this.attachFileSentSize = attachFileSentSize;
    }

    public Integer getAttachFileReceivedSize() {
        return attachFileReceivedSize;
    }

    public void setAttachFileReceivedSize(Integer attachFileReceivedSize) {
        this.attachFileReceivedSize = attachFileReceivedSize;
    }

    public Long getSenderAppUserId() {
        return senderAppUserId;
    }

    public void setSenderAppUserId(Long senderAppUserId) {
        this.senderAppUserId = senderAppUserId;
    }

    public Long getReceiverAppUserId() {
        return receiverAppUserId;
    }

    public void setReceiverAppUserId(Long receiverAppUserId) {
        this.receiverAppUserId = receiverAppUserId;
    }

    public AppUser getSenderAppUser() {
        return senderAppUser;
    }

    public void setSenderAppUser(AppUser senderAppUser) {
        this.senderAppUser = senderAppUser;
    }

    public boolean isLocalAttachFileExist() {
        return localAttachFileExist;
    }

    public void setLocalAttachFileExist(boolean localAttachFileExist) {
        this.localAttachFileExist = localAttachFileExist;
    }

    public Date getReadDateFrom() {
        return readDateFrom;
    }

    public void setReadDateFrom(Date readDateFrom) {
        this.readDateFrom = readDateFrom;
    }

    public Long getSenderAppUserIdNot() {
        return senderAppUserIdNot;
    }

    public void setSenderAppUserIdNot(Long senderAppUserIdNot) {
        this.senderAppUserIdNot = senderAppUserIdNot;
    }
}
