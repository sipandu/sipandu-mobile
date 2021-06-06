package com.sipanduteam.sipandu.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.AnakDataResponse;
import com.sipanduteam.sipandu.model.KeluargakuAnakResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeluargakuAnakRepository {
    private static KeluargakuAnakRepository keluargakuAnakRepository;

    public static KeluargakuAnakRepository getInstance(){
        if (keluargakuAnakRepository == null){
            keluargakuAnakRepository = new KeluargakuAnakRepository();
        }
        return keluargakuAnakRepository;
    }

    private InterfaceApi getData;

    public KeluargakuAnakRepository(){
        getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
    }

    public MutableLiveData<KeluargakuAnakResponse> getKeluargakuAnak(String anak){
        MutableLiveData<KeluargakuAnakResponse> keluargakuAnakResponseMutableLiveData = new MutableLiveData<>();
        Call<KeluargakuAnakResponse> keluargakuAnakResponseCall = getData.getKeluargakuAnak(anak);
        keluargakuAnakResponseCall.enqueue(new Callback<KeluargakuAnakResponse>() {
            @Override
            public void onResponse(Call<KeluargakuAnakResponse> call, Response<KeluargakuAnakResponse> response) {
                Log.d("aduh", String.valueOf(response.code()));
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    keluargakuAnakResponseMutableLiveData.setValue(response.body());
                }
                else {
                    keluargakuAnakResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<KeluargakuAnakResponse> call, Throwable t) {
                Log.d("aduhlagi", t.toString());
                Log.d("aduh", "masuk sini");
                keluargakuAnakResponseMutableLiveData.setValue(null);
            }
        });
        return keluargakuAnakResponseMutableLiveData;
    }
}
