package com.sipanduteam.sipandu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sipanduteam.sipandu.model.InformasiResponse;
import com.sipanduteam.sipandu.repository.InformasiBerandaRepository;

public class InformasiBerandaViewModel extends ViewModel {
    private MutableLiveData<InformasiResponse> informasiResponseMutableLiveData;
    private InformasiBerandaRepository informasiBerandaRepository;

    public void init(){
        if (informasiResponseMutableLiveData != null){
            return;
        }
        informasiBerandaRepository = informasiBerandaRepository.getInstance();
        informasiResponseMutableLiveData = informasiBerandaRepository.getInformasi("duar");

    }

    public LiveData<InformasiResponse> getInformasiRepository() {
        return informasiResponseMutableLiveData;
    }
}
