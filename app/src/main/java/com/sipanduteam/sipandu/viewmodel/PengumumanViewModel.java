package com.sipanduteam.sipandu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sipanduteam.sipandu.model.InformasiResponse;
import com.sipanduteam.sipandu.model.posyandu.PengumumanResponse;
import com.sipanduteam.sipandu.repository.InformasiRepository;
import com.sipanduteam.sipandu.repository.PengumumanRepository;

public class PengumumanViewModel extends ViewModel {
    private MutableLiveData<PengumumanResponse> pengumumanResponseMutableLiveData;
    private PengumumanRepository pengumumanRepository;

    public void init(String email, int role){
        if (pengumumanResponseMutableLiveData != null){
            return;
        }
        pengumumanRepository = pengumumanRepository.getInstance();
        pengumumanResponseMutableLiveData = pengumumanRepository.getPengumuman(email, role);

    }

    public void getData(String email, int role) {
        pengumumanResponseMutableLiveData = pengumumanRepository.getPengumuman(email, role);
    }

    public LiveData<PengumumanResponse> getPengumuman() {
        return pengumumanResponseMutableLiveData;
    }
}
