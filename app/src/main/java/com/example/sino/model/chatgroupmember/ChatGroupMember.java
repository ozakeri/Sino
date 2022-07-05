
package com.example.sino.model.chatgroupmember;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatGroupMember {

    @SerializedName("maxMember")
    @Expose
    public Integer maxMember;
    @SerializedName("notifyAct")
    @Expose
    public Boolean notifyAct;
    @SerializedName("name")
    @Expose
    public String name;
    @PrimaryKey
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("processStatus")
    @Expose
    public Integer processStatus;
    @SerializedName("privilegeTypeEn_text")
    @Expose
    public String privilegeTypeEnText;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("adminIs")
    @Expose
    public Boolean adminIs;
    @SerializedName("userId")
    @Expose
    public Integer userId;
    @SerializedName("processStatus_text")
    @Expose
    public String processStatusText;
    @SerializedName("expireDate")
    @Expose
    public String expireDate;
    @SerializedName("privilegeTypeEn")
    @Expose
    public Integer privilegeTypeEn;
    @SerializedName("status_text")
    @Expose
    public String statusText;
    @SerializedName("startDate")
    @Expose
    public String startDate;
    @SerializedName("userCreationId")
    @Expose
    public Integer userCreationId;


}
