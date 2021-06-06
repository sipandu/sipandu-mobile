package com.sipanduteam.sipandu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sipanduteam.sipandu.model.KeluargakuIbuResponse;
import com.sipanduteam.sipandu.model.KeluargakuLansiaResponse;
import com.sipanduteam.sipandu.repository.KeluargakuIbuRepository;
import com.sipanduteam.sipandu.repository.KeluargakuLansiaRepository;

public class KeluargakuLansiaViewModel extends ViewModel {
    private MutableLiveData<KeluargakuLansiaResponse> keluargakuLansiaResponseMutableLiveData;
    private KeluargakuLansiaRepository keluargakuLansiaRepository;

    public void init(String email){
        if (keluargakuLansiaResponseMutableLiveData != null){
            return;
        }
        keluargakuLansiaRepository = keluargakuLansiaRepository.getInstance();
        keluargakuLansiaResponseMutableLiveData = keluargakuLansiaRepository.getKeluargaLansia(email);
    }

    public void getData(String email) {
        keluargakuLansiaResponseMutableLiveData = keluargakuLansiaRepository.getKeluargaLansia(email);
    }

    public LiveData<KeluargakuLansiaResponse> getKeluargakuLansia() {
        return keluargakuLansiaResponseMutableLiveData;
    }
}
