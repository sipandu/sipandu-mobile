package com.sipanduteam.sipandu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sipanduteam.sipandu.model.KeluargakuAnakResponse;
import com.sipanduteam.sipandu.model.KeluargakuIbuResponse;
import com.sipanduteam.sipandu.repository.KeluargakuAnakRepository;
import com.sipanduteam.sipandu.repository.KeluargakuIbuRepository;

public class KeluargakuIbuViewModel extends ViewModel {
    private MutableLiveData<KeluargakuIbuResponse> keluargakuIbuViewModelMutableLiveData;
    private KeluargakuIbuRepository keluargakuIbuRepository;

    public void init(String email){
        if (keluargakuIbuViewModelMutableLiveData != null){
            return;
        }
        keluargakuIbuRepository = keluargakuIbuRepository.getInstance();
        keluargakuIbuViewModelMutableLiveData = keluargakuIbuRepository.getKeluargakuIbu(email);
    }

    public void getData(String email) {
        keluargakuIbuViewModelMutableLiveData = keluargakuIbuRepository.getKeluargakuIbu(email);
    }

    public LiveData<KeluargakuIbuResponse> getKeluargakuIbu() {
        return keluargakuIbuViewModelMutableLiveData;
    }
}
