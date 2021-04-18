package com.sipanduteam.sipandu.repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.api.BaseApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.InformasiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformasiBerandaRepository {
    private static InformasiBerandaRepository informasiBerandaRepository;

    public static InformasiBerandaRepository getInstance(){
        if (informasiBerandaRepository == null){
            informasiBerandaRepository = new InformasiBerandaRepository();
        }
        return informasiBerandaRepository;
    }

    private BaseApi getData;

    public InformasiBerandaRepository(){
        getData = RetrofitClient.buildRetrofit().create(BaseApi.class);
    }

    public MutableLiveData<InformasiResponse> getInformasi(String apaje){
        MutableLiveData<InformasiResponse> informasiResponseMutableLiveData = new MutableLiveData<>();
        Call<InformasiResponse> informasiResponseCall = getData.getInformasiHome();
        informasiResponseCall.enqueue(new Callback<InformasiResponse>() {
            @Override
            public void onResponse(Call<InformasiResponse> call, Response<InformasiResponse> response) {
                Log.d("duar", String.valueOf(response.code()));
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    informasiResponseMutableLiveData.setValue(response.body());
                }
                else {
                    informasiResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<InformasiResponse> call, Throwable t) {
                informasiResponseMutableLiveData.setValue(null);
            }
        });
        return informasiResponseMutableLiveData;
    }
}
