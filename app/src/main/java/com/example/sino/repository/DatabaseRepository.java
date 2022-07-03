package com.example.sino.repository;

import com.example.sino.api.NetworkApi;
import com.example.sino.db.SinoDao;
import com.example.sino.model.db.ChatGroup;
import com.example.sino.model.db.ChatMessage;
import com.example.sino.model.db.UserPermission;

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

    public Long insertChatMessageRepo(ChatMessage chatMessage) {
        return sinoDao.insertChatMessage(chatMessage);
    }
}
