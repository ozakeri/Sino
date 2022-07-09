package com.example.sino.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sino.model.chatgroup.ChatGroup;
import com.example.sino.model.chatgroupmember.ChatGroupMember;
import com.example.sino.model.db.AppUser;
import com.example.sino.model.db.ChatMessage;
import com.example.sino.model.db.User;
import com.example.sino.model.db.UserPermission;

import java.util.List;

@Dao
public interface SinoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPermission(UserPermission permission);

    @Insert
    void insertChatMessage(ChatMessage chatMessage);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void updateChatMessage(ChatMessage chatMessage);

    @Insert()
    void insertChatGroup(ChatGroup chatGroup);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void updateChatGroup(ChatGroup chatGroup);

    @Insert()
    void insertAppUser(AppUser appUser);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void updateAppUser(AppUser appUser);

    @Query("SELECT * FROM sino_table WHERE mobileNo=:mobileNo")
    User getUserByMobileNo(String mobileNo);

    @Query("SELECT * FROM sino_table")
    LiveData<List<User>> getAllUser();

    @Query("SELECT * FROM sino_table")
    List<ChatGroup> getChatGroupList();

    @Query("SELECT * FROM permission WHERE userId=:userId")
    List<UserPermission> getUserPermissionListByUserId(Long userId);

    @Query("SELECT * FROM chat_group WHERE status=:status")
    List<ChatGroup> getActiveChatGroupList(int status);

    @Query("SELECT * FROM appuser WHERE id=:id")
    AppUser getAppUserById(Long id);

    @Query("SELECT * FROM chat_message WHERE serverMessageId=:id")
    List<ChatMessage> getChatMessagesByServerMessageId(Long id);

    @Query("SELECT * FROM chatgroupmember WHERE id=:id")
    ChatGroup getChatGroupListByParam(Long id);

    @Query("DELETE FROM permission WHERE userId=:userId & permissionName=:permissionName")
    void deletePermission(Long userId, String permissionName);

    @Query("DELETE FROM permission WHERE userId=:userId")
    void deletePermissionByUserId(Long userId);

    @Query("SELECT * FROM chat_group WHERE serverGroupId=:serverGroupId")
    ChatGroup getChatGroupByServerGroupId(Long serverGroupId);

    @Query("SELECT * FROM chatgroupmember WHERE userId=:userId AND chatGroupId=:chatGroupId")
    ChatGroupMember getChatGroupMemberByUserAndGroup(Long userId, Long chatGroupId);

    @Query("SELECT * FROM chatgroupmember WHERE userId=:userId AND chatGroupId=:chatGroupId")
    ChatGroupMember getChatGroupMemberListByParam(Long userId, Long chatGroupId);

    @Insert()
    void insertChatGroupMember(ChatGroupMember chatGroupMember);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void updateChatGroupMember(ChatGroupMember chatGroupMember);

}
