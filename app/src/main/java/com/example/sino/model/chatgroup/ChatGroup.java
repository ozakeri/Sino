
package com.example.sino.model.chatgroup;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.sino.model.db.ChatGroupMember;
import com.example.sino.model.db.ChatMessage;
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

    private Long serverGroupId;
    private Boolean privateIs;
    private Integer statusEn;
    private List<ChatGroupMember> chatGroupMemberList;
    private List<ChatMessage> chatMessageList;
    private ChatMessage lastChatMessage;
    private Integer countOfUnreadMessage;
    private List<Long> notServerGroupIdList;
    private Integer countOfMembers;

}
