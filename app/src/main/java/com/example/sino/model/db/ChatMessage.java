package com.example.sino.model.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.sino.utils.converters.AppUserConverter;
import com.example.sino.utils.converters.ChatMessageConverter;
import com.example.sino.utils.converters.DateConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "chat_message")
public class ChatMessage {

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
    @PrimaryKey
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
    @TypeConverters({DateConverter.class})
    private java.util.Date validUntilDate;
    private String message;
    private String attachFileLocalPath;
    private String attachFileRemoteUrl;
    private Boolean deliverIs;
    @TypeConverters({DateConverter.class})
    private java.util.Date deliverDate;
    private Boolean readIs;
    @TypeConverters({DateConverter.class})
    private java.util.Date readDate;
    private Integer sendingStatusEn;
    @TypeConverters({DateConverter.class})
    private java.util.Date sendingStatusDate;
    private Integer attachFileSentSize;
    private Integer attachFileReceivedSize;
    private Long senderAppUserId;
    private Long receiverAppUserId;
    @TypeConverters({AppUserConverter.class})
    private AppUser senderAppUser;
    private boolean localAttachFileExist;
    @TypeConverters({DateConverter.class})
    private java.util.Date readDateFrom;
    private Long senderAppUserIdNot;
    private Boolean isCreateNewPvChatGroup;

    public ChatMessage() {
    }


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

    public Boolean getCreateNewPvChatGroup() {
        return isCreateNewPvChatGroup;
    }

    public void setCreateNewPvChatGroup(Boolean createNewPvChatGroup) {
        isCreateNewPvChatGroup = createNewPvChatGroup;
    }
}
