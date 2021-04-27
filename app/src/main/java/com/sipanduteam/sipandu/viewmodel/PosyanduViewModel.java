package com.sipanduteam.sipandu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sipanduteam.sipandu.model.posyandu.PosyanduUserResponse;
import com.sipanduteam.sipandu.repository.PosyanduRepository;

public class PosyanduViewModel extends ViewModel {
    private MutableLiveData<PosyanduUserResponse> posyanduUserResponseMutableLiveData;
    private PosyanduRepository posyanduRepository;

    public void init(String email, int role){
        if (posyanduUserResponseMutableLiveData != null){
            return;
        }
        posyanduRepository = posyanduRepository.getInstance();
        posyanduUserResponseMutableLiveData = posyanduRepository.getUserPosyandu(email, role);
    }

    public void getData(String email, int role) {
        posyanduUserResponseMutableLiveData = posyanduRepository.getUserPosyandu(email, role);
    }

    public LiveData<PosyanduUserResponse> getUserPosyandu() {
        return posyanduUserResponseMutableLiveData;
    }
}
