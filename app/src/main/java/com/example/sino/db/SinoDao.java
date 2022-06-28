package com.example.sino.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sino.model.db.ChatGroup;
import com.example.sino.model.db.User;
import com.example.sino.model.db.UserPermission;

import java.util.List;

@Dao
public interface SinoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPermission(UserPermission permission);

    @Query("SELECT * FROM sino_table WHERE mobileNo=:mobileNo")
    User getUserByMobileNo(String mobileNo);

    @Query("SELECT * FROM sino_table")
    LiveData<List<User>> getAllUser();

    @Query("SELECT * FROM sino_table")
    List<ChatGroup> getChatGroupList();

    @Query("SELECT * FROM permission WHERE userId=:userId")
    List<UserPermission> getUserPermissionListByUserId(Long userId);

    @Query("DELETE FROM permission WHERE userId=:userId & permissionName=:permissionName")
    void deletePermission(Long userId, String permissionName);

    @Query("DELETE FROM permission WHERE userId=:userId")
    void deletePermissionByUserId(Long userId);

}
