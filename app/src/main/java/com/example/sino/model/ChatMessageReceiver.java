package com.example.sino.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.sino.model.db.ChatMessage;
import com.example.sino.utils.converters.ChatMessageConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatMessageReceiver implements Parcelable {
    @SerializedName("deliverIs")
    @Expose
    public Boolean deliverIs;
    @SerializedName("dateCreation")
    @Expose
    public String dateCreation;
    @SerializedName("readIs")
    @Expose
    public Boolean readIs;
    @SerializedName("readDate")
    @Expose
    public String readDate;
    @SerializedName("deliverDate")
    @Expose
    public String deliverDate;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("status_text")
    @Expose
    public String statusText;

    @TypeConverters(ChatMessageConverter.class)
    @SerializedName("chatMessage")
    @Expose
    public ChatMessage chatMessage;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("receiverUserId")
    @Expose
    public Integer receiverUserId;
    @SerializedName("userCreationId")
    @Expose
    public Integer userCreationId;

    protected ChatMessageReceiver(Parcel in) {
        byte tmpDeliverIs = in.readByte();
        deliverIs = tmpDeliverIs == 0 ? null : tmpDeliverIs == 1;
        dateCreation = in.readString();
        byte tmpReadIs = in.readByte();
        readIs = tmpReadIs == 0 ? null : tmpReadIs == 1;
        readDate = in.readString();
        deliverDate = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        statusText = in.readString();
        chatMessage = in.readParcelable(ChatMessage.class.getClassLoader());
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        if (in.readByte() == 0) {
            receiverUserId = null;
        } else {
            receiverUserId = in.readInt();
        }
        if (in.readByte() == 0) {
            userCreationId = null;
        } else {
            userCreationId = in.readInt();
        }
    }

    public static final Creator<ChatMessageReceiver> CREATOR = new Creator<ChatMessageReceiver>() {
        @Override
        public ChatMessageReceiver createFromParcel(Parcel in) {
            return new ChatMessageReceiver(in);
        }

        @Override
        public ChatMessageReceiver[] newArray(int size) {
            return new ChatMessageReceiver[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (deliverIs == null ? 0 : deliverIs ? 1 : 2));
        parcel.writeString(dateCreation);
        parcel.writeByte((byte) (readIs == null ? 0 : readIs ? 1 : 2));
        parcel.writeString(readDate);
        parcel.writeString(deliverDate);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(statusText);
        parcel.writeParcelable(chatMessage, i);
        if (status == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(status);
        }
        if (receiverUserId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(receiverUserId);
        }
        if (userCreationId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(userCreationId);
        }
    }

    public Boolean getDeliverIs() {
        return deliverIs;
    }

    public void setDeliverIs(Boolean deliverIs) {
        this.deliverIs = deliverIs;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean getReadIs() {
        return readIs;
    }

    public void setReadIs(Boolean readIs) {
        this.readIs = readIs;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }

    public String getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
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

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(Integer receiverUserId) {
        this.receiverUserId = receiverUserId;
    }
}
