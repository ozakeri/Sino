package com.example.sino.viewmodel;

import android.content.Context;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.sino.api.NetworkRepository;
import com.example.sino.model.SuccessRegisterBean;
import com.example.sino.model.db.User;

import java.util.List;

public class MainViewModel extends ViewModel {

    private NetworkRepository repository;

    @ViewModelInject
    public MainViewModel(NetworkRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<User>> getAllUser() {
        return repository.getAllUser();
    }



    public void insertUser(User user) {
        repository.insertUser(user);
    }
}
