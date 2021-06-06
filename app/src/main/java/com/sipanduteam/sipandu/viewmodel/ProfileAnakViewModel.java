package com.sipanduteam.sipandu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sipanduteam.sipandu.model.AnakDataResponse;
import com.sipanduteam.sipandu.repository.ProfileAnakRepository;

public class ProfileAnakViewModel extends ViewModel {
    private MutableLiveData<AnakDataResponse> anakDataResponseMutableLiveData;
    private ProfileAnakRepository profileAnakRepository;

    public void init(String anak){
        if (anakDataResponseMutableLiveData != null){
            return;
        }
        profileAnakRepository = profileAnakRepository.getInstance();
        anakDataResponseMutableLiveData = profileAnakRepository.getProfileAnak(anak);
    }

    public void getData(String anak) {
        anakDataResponseMutableLiveData = profileAnakRepository.getProfileAnak(anak);
    }

    public LiveData<AnakDataResponse> getProfileAnakRepository() {
        return anakDataResponseMutableLiveData;
    }
}
