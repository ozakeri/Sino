
package com.example.sino.model.chatgroup;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("chatGroupList")
    @Expose
    public List<ChatGroup> chatGroupList = null;

}
