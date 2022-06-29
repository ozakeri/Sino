package com.example.sino.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sino.model.db.ChatMessage;
import com.example.sino.model.db.User;
import com.example.sino.model.db.UserPermission;

@Database(entities = {User.class, UserPermission.class, ChatMessage.class},version = 1)
public abstract class SinoDatabase extends RoomDatabase {

    public abstract SinoDao sinoDao();
}
