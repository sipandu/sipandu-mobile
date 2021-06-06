package com.sipanduteam.sipandu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sipanduteam.sipandu.model.LansiaDataResponse;
import com.sipanduteam.sipandu.repository.ProfileLansiaRepository;

public class ProfileLansiaViewModel extends ViewModel {
    private MutableLiveData<LansiaDataResponse> lansiaDataResponseMutableLiveData;
    private ProfileLansiaRepository profileLansiaRepository;

    public void init(String lansia){
        if (lansiaDataResponseMutableLiveData != null){
            return;
        }
        profileLansiaRepository = profileLansiaRepository.getInstance();
        lansiaDataResponseMutableLiveData = profileLansiaRepository.getProfileLansia(lansia);
    }

    public void getData(String lansia) {
        lansiaDataResponseMutableLiveData = profileLansiaRepository.getProfileLansia(lansia);
    }

    public LiveData<LansiaDataResponse> getProfileLansiaRepository() {
        return lansiaDataResponseMutableLiveData;
    }
}
