package com.sipanduteam.sipandu.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.KeluargakuLansiaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeluargakuLansiaRepository {
    private static KeluargakuLansiaRepository keluargakuLansiaRepository;

    public static KeluargakuLansiaRepository getInstance(){
        if (keluargakuLansiaRepository == null){
            keluargakuLansiaRepository = new KeluargakuLansiaRepository();
        }
        return keluargakuLansiaRepository;
    }

    private InterfaceApi getData;

    public KeluargakuLansiaRepository(){
        getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
    }

    public MutableLiveData<KeluargakuLansiaResponse> getKeluargaLansia(String lansia){
        MutableLiveData<KeluargakuLansiaResponse> keluargakuLansiaResponseMutableLiveData = new MutableLiveData<>();
        Call<KeluargakuLansiaResponse> keluargakuLansiaResponseCall = getData.getKeluargakuLansia(lansia);
        keluargakuLansiaResponseCall.enqueue(new Callback<KeluargakuLansiaResponse>() {
            @Override
            public void onResponse(Call<KeluargakuLansiaResponse> call, Response<KeluargakuLansiaResponse> response) {
                Log.d("aduh", String.valueOf(response.code()));
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    keluargakuLansiaResponseMutableLiveData.setValue(response.body());
                }
                else {
                    keluargakuLansiaResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<KeluargakuLansiaResponse> call, Throwable t) {
                Log.d("aduhlagi", t.toString());
                Log.d("aduh", "masuk sini");
                keluargakuLansiaResponseMutableLiveData.setValue(null);
            }
        });
        return keluargakuLansiaResponseMutableLiveData;
    }
}
