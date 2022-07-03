package com.example.sino.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

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

    public Long insertChatMessageVM(ChatMessage chatMessage) {
        return repository.insertChatMessageRepo(chatMessage);
    }
}
