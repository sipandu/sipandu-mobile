package com.sipanduteam.sipandu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sipanduteam.sipandu.model.AnakDataResponse;
import com.sipanduteam.sipandu.model.IbuDataResponse;
import com.sipanduteam.sipandu.repository.ProfileAnakRepository;
import com.sipanduteam.sipandu.repository.ProfileIbuRepository;

public class ProfileIbuViewModel extends ViewModel {
    private MutableLiveData<IbuDataResponse> ibuDataResponseMutableLiveData;
    private ProfileIbuRepository profileIbuRepository;

    public void init(String ibu){
        if (ibuDataResponseMutableLiveData != null){
            return;
        }
        profileIbuRepository = profileIbuRepository.getInstance();
        ibuDataResponseMutableLiveData = profileIbuRepository.getProfileIbu(ibu);
    }

    public void getData(String ibu) {
        ibuDataResponseMutableLiveData = profileIbuRepository.getProfileIbu(ibu);
    }

    public LiveData<IbuDataResponse> getProfileIbuRepository() {
        return ibuDataResponseMutableLiveData;
    }

}
