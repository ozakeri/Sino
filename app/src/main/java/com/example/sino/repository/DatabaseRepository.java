package com.example.sino.repository;

import com.example.sino.db.SinoDao;
import com.example.sino.model.chatgroup.ChatGroup;
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

    public List<ChatGroup> getChatGroupListByParamRepo(List<Long> notServerGroupIdList) {
        return sinoDao.getChatGroupListByParam(notServerGroupIdList);
    }

    public ChatGroup getChatGroupByServerGroupIdRepo(Long id) {
        return sinoDao.getChatGroupByServerGroupId(id);
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

    public List<ChatGroup> getChatGroupList() {
        return sinoDao.getChatGroupList();
    }
}
