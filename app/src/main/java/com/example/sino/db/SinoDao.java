package com.example.sino.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sino.model.db.User;

import java.util.List;

@Dao
public interface SinoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("SELECT * FROM user WHERE mobileNo=:mobileNo")
    User getUserByMobileNo(String mobileNo);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUser();
}
