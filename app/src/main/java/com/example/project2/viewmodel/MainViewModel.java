package com.example.project2.viewmodel;

import com.example.project2.livedata.AuthLiveData;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private AuthLiveData authLiveData;

    public MainViewModel() {
        authLiveData = new AuthLiveData();
    }

    public AuthLiveData getAuthLiveData(){
        return authLiveData;
    }
}
