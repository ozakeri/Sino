package com.example.sino.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.sino.model.chatgroup.ChatGroup;
import com.example.sino.model.chatgroupmember.ChatGroupMember;
import com.example.sino.model.db.AppUser;
import com.example.sino.model.db.ChatMessage;
import com.example.sino.repository.DatabaseRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DatabaseViewModel extends AndroidViewModel {

    @Inject
    DatabaseRepository repository;

    @Inject
    public DatabaseViewModel(@NonNull Application application) {
        super(application);
    }

    public List<ChatMessage> getChatMessagesByServerMessageIdVM(Long id) {
        return repository.getChatMessagesByServerMessageIdRepo(id);
    }

    public void insertChatMessageVM(ChatMessage chatMessage) {
         repository.insertChatMessageRepo(chatMessage);
    }

    public void updateChatMessageVM(ChatMessage chatMessage) {
         repository.updateChatMessageRepo(chatMessage);
    }

    public void insertChatGroupVM(ChatGroup chatGroup) {
        repository.insertChatGroupRepo(chatGroup);
    }

    public void updateChatGroupVM(ChatGroup chatGroup) {
        repository.updateChatGroupRepo(chatGroup);
    }

    public void insertAppUserVM(AppUser appUser) {
        repository.insertAppUserRepo(appUser);
    }

    public void updateAppUserVM(AppUser appUser) {
        repository.updateAppUserRepo(appUser);
    }

    public void insertChatGroupMemberVM(ChatGroupMember chatGroupMember) {
        repository.insertChatGroupMemberRepo(chatGroupMember);
    }

    public void updateChatGroupMemberVM(ChatGroupMember chatGroupMember) {
        repository.updateChatGroupMemberRepo(chatGroupMember);
    }

    public List<ChatGroup> getChatGroupListVM() {
        return repository.getChatGroupList();
    }

    public List<ChatGroup> getActiveChatGroupListVM(int status) {
        return repository.getActiveChatGroupListRepo(status);
    }

    public ChatGroup getChatGroupByServerGroupIdVM(Long id) {
        return repository.getChatGroupByServerGroupIdRepo(id);
    }

    public ChatGroupMember getChatGroupMemberByUserAndGroupVM(Long userId, Long chatGroupId) {
        return repository.getChatGroupMemberByUserAndGroupRepo(userId, chatGroupId);
    }

    public ChatGroup getChatGroupListByParamVM(Long id) {
        return repository.getChatGroupListByParamRepo(id);
    }
}
