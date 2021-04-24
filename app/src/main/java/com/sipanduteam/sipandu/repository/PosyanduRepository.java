package com.sipanduteam.sipandu.repository;

import androidx.lifecycle.MutableLiveData;

import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.posyandu.PosyanduUserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PosyanduRepository {
    private static PosyanduRepository posyanduRepository;

    public static PosyanduRepository getInstance(){
        if (posyanduRepository == null){
            posyanduRepository = new PosyanduRepository();
        }
        return posyanduRepository;
    }

    private InterfaceApi getData;

    public PosyanduRepository(){
        getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
    }

    public MutableLiveData<PosyanduUserResponse> getUserPosyandu(String email, int role){
        MutableLiveData<PosyanduUserResponse> posyanduUserResponseMutableLiveData = new MutableLiveData<>();
        Call<PosyanduUserResponse> posyanduUserResponseCall = getData.posyanduUserData(email, role);
        posyanduUserResponseCall.enqueue(new Callback<PosyanduUserResponse>() {
            @Override
            public void onResponse(Call<PosyanduUserResponse> call, Response<PosyanduUserResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    posyanduUserResponseMutableLiveData.setValue(response.body());
                }
                else {
                    posyanduUserResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<PosyanduUserResponse> call, Throwable t) {
                posyanduUserResponseMutableLiveData.setValue(null);
            }
        });
        return posyanduUserResponseMutableLiveData;
    }
}
