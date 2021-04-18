package com.sipanduteam.sipandu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sipanduteam.sipandu.model.AnakDataResponse;
import com.sipanduteam.sipandu.model.Posyandu;
import com.sipanduteam.sipandu.model.PosyanduUserResponse;
import com.sipanduteam.sipandu.repository.PosyanduRepository;
import com.sipanduteam.sipandu.repository.ProfileAnakRepository;

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

    public LiveData<PosyanduUserResponse> getUserPosyandu() {
        return posyanduUserResponseMutableLiveData;
    }
}
