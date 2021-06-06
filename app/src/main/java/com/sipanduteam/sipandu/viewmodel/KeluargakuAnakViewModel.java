package com.sipanduteam.sipandu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sipanduteam.sipandu.model.KeluargakuAnakResponse;
import com.sipanduteam.sipandu.model.posyandu.PosyanduUserResponse;
import com.sipanduteam.sipandu.repository.KeluargakuAnakRepository;
import com.sipanduteam.sipandu.repository.PosyanduRepository;

public class KeluargakuAnakViewModel extends ViewModel {
    private MutableLiveData<KeluargakuAnakResponse> keluargakuAnakResponseMutableLiveData;
    private KeluargakuAnakRepository keluargakuAnakRepository;

    public void init(String email){
        if (keluargakuAnakResponseMutableLiveData != null){
            return;
        }
        keluargakuAnakRepository = keluargakuAnakRepository.getInstance();
        keluargakuAnakResponseMutableLiveData = keluargakuAnakRepository.getKeluargakuAnak(email);
    }

    public void getData(String email) {
        keluargakuAnakResponseMutableLiveData = keluargakuAnakRepository.getKeluargakuAnak(email);
    }

    public LiveData<KeluargakuAnakResponse> getKeluargakuAnak() {
        return keluargakuAnakResponseMutableLiveData;
    }
}
