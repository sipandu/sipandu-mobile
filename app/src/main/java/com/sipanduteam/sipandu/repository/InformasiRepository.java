package com.sipanduteam.sipandu.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.InformasiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformasiRepository {
    private static InformasiRepository informasiRepository;

    public static InformasiRepository getInstance(){
        if (informasiRepository == null){
            informasiRepository = new InformasiRepository();
        }
        return informasiRepository;
    }

    private InterfaceApi getData;

    public InformasiRepository(){
        getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
    }

    public MutableLiveData<InformasiResponse> getInformasi(int flag){
        MutableLiveData<InformasiResponse> informasiResponseMutableLiveData = new MutableLiveData<>();
        Call<InformasiResponse> informasiResponseCall = getData.getInformasi(flag);
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
    public MutableLiveData<InformasiResponse> getInformasiHome(){
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
