package com.sipanduteam.sipandu.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.posyandu.PengumumanResponse;
import com.sipanduteam.sipandu.model.posyandu.PosyanduUserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengumumanRepository {
    private static PengumumanRepository pengumumanRepository;

    public static PengumumanRepository getInstance(){
        if (pengumumanRepository == null){
            pengumumanRepository = new PengumumanRepository();
        }
        return pengumumanRepository;
    }

    private InterfaceApi getData;

    public PengumumanRepository(){
        getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
    }

    public MutableLiveData<PengumumanResponse> getPengumuman(String email, int role){
        MutableLiveData<PengumumanResponse> pengumumanResponseMutableLiveData = new MutableLiveData<>();
        Call<PengumumanResponse> pengumumanResponseCall = getData.pengumumanPosyanduData(email, role);
        pengumumanResponseCall.enqueue(new Callback<PengumumanResponse>() {
            @Override
            public void onResponse(Call<PengumumanResponse> call, Response<PengumumanResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    pengumumanResponseMutableLiveData.setValue(response.body());
                }
                else {
                    pengumumanResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<PengumumanResponse> call, Throwable t) {
                pengumumanResponseMutableLiveData.setValue(null);
            }
        });
        return pengumumanResponseMutableLiveData;
    }
}
