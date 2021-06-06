package com.sipanduteam.sipandu.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.KeluargakuAnakResponse;
import com.sipanduteam.sipandu.model.KeluargakuIbuResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeluargakuIbuRepository {
    private static KeluargakuIbuRepository keluargakuIbuRepository;

    public static KeluargakuIbuRepository getInstance(){
        if (keluargakuIbuRepository == null){
            keluargakuIbuRepository = new KeluargakuIbuRepository();
        }
        return keluargakuIbuRepository;
    }

    private InterfaceApi getData;

    public KeluargakuIbuRepository(){
        getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
    }

    public MutableLiveData<KeluargakuIbuResponse> getKeluargakuIbu(String ibu){
        MutableLiveData<KeluargakuIbuResponse> keluargakuIbuResponseMutableLiveData = new MutableLiveData<>();
        Call<KeluargakuIbuResponse> keluargakuAnakResponseCall = getData.getKeluargakuIbu(ibu);
        keluargakuAnakResponseCall.enqueue(new Callback<KeluargakuIbuResponse>() {
            @Override
            public void onResponse(Call<KeluargakuIbuResponse> call, Response<KeluargakuIbuResponse> response) {
                Log.d("aduh", String.valueOf(response.code()));
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    keluargakuIbuResponseMutableLiveData.setValue(response.body());
                }
                else {
                    keluargakuIbuResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<KeluargakuIbuResponse> call, Throwable t) {
                Log.d("aduhlagi", t.toString());
                Log.d("aduh", "masuk sini");
                keluargakuIbuResponseMutableLiveData.setValue(null);
            }
        });
        return keluargakuIbuResponseMutableLiveData;
    }
}
