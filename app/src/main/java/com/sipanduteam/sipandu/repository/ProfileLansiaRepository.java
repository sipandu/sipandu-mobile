package com.sipanduteam.sipandu.repository;

import androidx.lifecycle.MutableLiveData;

import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.AnakDataResponse;
import com.sipanduteam.sipandu.model.LansiaDataResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileLansiaRepository {
    private static ProfileLansiaRepository profileLansiaRepository;

    public static ProfileLansiaRepository getInstance(){
        if (profileLansiaRepository == null){
            profileLansiaRepository = new ProfileLansiaRepository();
        }
        return profileLansiaRepository;
    }

    private InterfaceApi getData;

    public ProfileLansiaRepository(){
        getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
    }

    public MutableLiveData<LansiaDataResponse> getProfileLansia(String lansia){
        MutableLiveData<LansiaDataResponse> lansiaDataResponseMutableLiveData = new MutableLiveData<>();
        Call<LansiaDataResponse> lansiaDataResponseCall = getData.lansiaData(lansia);
        lansiaDataResponseCall.enqueue(new Callback<LansiaDataResponse>() {
            @Override
            public void onResponse(Call<LansiaDataResponse> call, Response<LansiaDataResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    lansiaDataResponseMutableLiveData.setValue(response.body());
                }
                else {
                    lansiaDataResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LansiaDataResponse> call, Throwable t) {
                lansiaDataResponseMutableLiveData.setValue(null);
            }
        });
        return lansiaDataResponseMutableLiveData;
    }
}
