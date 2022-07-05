package com.example.sino.repository;

import com.example.sino.db.SinoDao;
import com.example.sino.model.chatgroup.ChatGroup;
import com.example.sino.model.chatgroupmember.ChatGroupMember;
import com.example.sino.model.db.ChatMessage;

import java.util.List;

import javax.inject.Inject;

public class DatabaseRepository {

    private SinoDao sinoDao;

    @Inject
    public DatabaseRepository(SinoDao sinoDao) {
        this.sinoDao = sinoDao;
    }

    public List<ChatMessage> getChatMessagesByServerMessageIdRepo(Long id) {
        return sinoDao.getChatMessagesByServerMessageId(id);
    }

    public ChatGroup getChatGroupListByParamRepo(Long id) {
        return sinoDao.getChatGroupListByParam(id);
    }

    public ChatGroup getChatGroupByServerGroupIdRepo(Long id) {
        return sinoDao.getChatGroupByServerGroupId(id);
    }

    public ChatGroupMember getChatGroupMemberByUserAndGroupRepo(Long userId, Long chatGroupId) {
        return sinoDao.getChatGroupMemberByUserAndGroup(userId, chatGroupId);
    }

    public ChatGroupMember getChatGroupMemberListByParamRepo(Long userId, Long chatGroupId) {
        return sinoDao.getChatGroupMemberListByParam(userId, chatGroupId);
    }

    public Long insertChatMessageRepo(ChatMessage chatMessage) {
        return sinoDao.insertChatMessage(chatMessage);
    }

    public void insertChatGroupRepo(ChatGroup chatGroup) {
        sinoDao.insertChatGroup(chatGroup);
    }

    public void updateChatGroupRepo(ChatGroup chatGroup) {
        sinoDao.updateChatGroup(chatGroup);
    }

    public void insertChatGroupMemberRepo(ChatGroupMember chatGroupMember) {
        sinoDao.insertChatGroupMember(chatGroupMember);
    }

    public void updateChatGroupMemberRepo(ChatGroupMember chatGroupMember) {
        sinoDao.updateChatGroupMember(chatGroupMember);
    }

    public List<ChatGroup> getChatGroupList() {
        return sinoDao.getChatGroupList();
    }
}
