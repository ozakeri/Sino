package com.example.sino.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sino.model.chatgroup.ChatGroup;
import com.example.sino.model.chatgroupmember.ChatGroupMember;
import com.example.sino.model.db.AppUser;
import com.example.sino.model.db.ChatMessage;
import com.example.sino.model.db.User;
import com.example.sino.model.db.UserPermission;

@Database(entities = {User.class, UserPermission.class, ChatMessage.class, ChatGroup.class, ChatGroupMember.class, AppUser.class}, version = 2)
public abstract class SinoDatabase extends RoomDatabase {

    public abstract SinoDao sinoDao();
}
