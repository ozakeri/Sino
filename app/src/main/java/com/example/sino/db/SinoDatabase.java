package com.example.sino.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sino.model.db.User;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class SinoDatabase extends RoomDatabase {

    public abstract SinoDao sinoDao();
}
